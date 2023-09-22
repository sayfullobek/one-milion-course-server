package it.revo.onemilioncourse.fullImpl;


import it.revo.onemilioncourse.payload.ProductDto;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface ProductControllerImpl {
    HttpEntity<?> getAll();

    HttpEntity<?> addProduct(ProductDto productDto, UUID userId);

    HttpEntity<?> deleteProduct(UUID productId, UUID userId);

    HttpEntity<?> editProduct(UUID productId, ProductDto productDto, UUID userId);

    HttpEntity<?> getOne(UUID productId);

//    HttpEntity<?> getProductByCategoryId(Integer categoryId);
}
