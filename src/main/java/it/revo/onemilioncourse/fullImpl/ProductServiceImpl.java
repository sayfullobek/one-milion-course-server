package it.revo.onemilioncourse.fullImpl;

import it.revo.onemilioncourse.entity.Product;
import it.revo.onemilioncourse.payload.ApiResponse;
import it.revo.onemilioncourse.payload.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductServiceImpl {

    ApiResponse<?> addProduct(ProductDto productDto, UUID userId);

    ApiResponse<?> deleteProduct(UUID productId, UUID userId);

    ApiResponse<?> editProduct(UUID productId, ProductDto productDto, UUID userId);

    List<Product> getProductByCategoryId(Integer categoryId);

    List<Product> search(String search);
}
