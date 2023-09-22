package it.revo.onemilioncourse.controller;

import it.revo.onemilioncourse.entity.Product;
import it.revo.onemilioncourse.exception.ResourceNotFoundException;
import it.revo.onemilioncourse.fullImpl.ProductControllerImpl;
import it.revo.onemilioncourse.payload.ApiResponse;
import it.revo.onemilioncourse.payload.ProductDto;
import it.revo.onemilioncourse.repository.ProductRepository;
import it.revo.onemilioncourse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController implements ProductControllerImpl {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Product> all = productRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @Override
    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto,
                                    @RequestParam(name = "userId", required = false) UUID userId) {
        ApiResponse<?> apiResponse = productService.addProduct(productDto, userId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @Override
    @DeleteMapping("/{productId}")
    public HttpEntity<?> deleteProduct(@PathVariable UUID productId,
                                       @RequestParam(name = "userId", required = false) UUID userId) {
        return null;
    }

    @Override
    @PutMapping("/{productId}")
    public HttpEntity<?> editProduct(@PathVariable UUID productId,
                                     @RequestBody ProductDto productDto,
                                     @RequestParam(name = "userId", required = false) UUID userId) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getProduct", "productId", id)));
    }

//    @Override
//    @GetMapping("/{categoryId}")
//    public HttpEntity<?> getProductByCategoryId(@PathVariable Integer categoryId) {
//        return null;
//    }
}
