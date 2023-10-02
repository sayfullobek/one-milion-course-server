package it.revo.onemilioncourse.service;

import it.revo.onemilioncourse.entity.Category;
import it.revo.onemilioncourse.entity.Product;
import it.revo.onemilioncourse.entity.User;
import it.revo.onemilioncourse.exception.ResourceNotFoundException;
import it.revo.onemilioncourse.fullImpl.ProductServiceImpl;
import it.revo.onemilioncourse.payload.ApiResponse;
import it.revo.onemilioncourse.payload.ProductDto;
import it.revo.onemilioncourse.repository.ProductRepository;
import it.revo.onemilioncourse.repository.UserRepository;
import it.revo.onemilioncourse.repository.rest.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<?> addProduct(ProductDto productDto, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(401, "getUser", "userId", userId));
        if (user == null) {
            return new ApiResponse<>("Siz bu yo'lga kira olmaysiz", false, 401);
        }
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(404, "getCategory", "categoryId", productDto.getCategoryId()));
        if (category == null) {
            return new ApiResponse<>("Bunday bo'lim mavjud emas", false, 404);
        }
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .photoId(productDto.getPhotoId())
                .category(category)
                .build();
        productRepository.save(product);
        return new ApiResponse<>("Muvaffaqiyatli saqlandi", true, 200);
    }

    @Override
    public ApiResponse<?> deleteProduct(UUID productId, UUID userId) {
        return null;
    }

    @Override
    public ApiResponse<?> editProduct(UUID productId, ProductDto productDto, UUID userId) {
        return null;
    }

    @Override
    public List<Product> getProductByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> search(String search) {
        return productRepository.searchByName(search);
    }

    @Override
    public ApiResponse<?> likeAndBasketProducts(Long chatId, UUID productId, String status) {
        User user = userRepository.findUserByChatId(chatId);
        int tr = 0;
        if (status.equals("like")) {
            Set<Product> likeProducts = user.getLikeProducts();
            for (Product likeProduct : likeProducts) {
                if (likeProduct.getId() == productId) {
                    user.getLikeProducts().remove(likeProduct);
                    tr++;
                }
            }
            if (tr == 0) {
                user.getLikeProducts().add(productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(404, "getProduct", "productId", productId)));
            }
        } else {
            List<Product> basketProducts = user.getBaskets();
            for (Product basketProduct : basketProducts) {
                if (basketProduct.getId() == productId) {
                    user.getBaskets().remove(basketProduct);
                    tr++;
                }
            }
            if (tr == 0) {
                user.getBaskets().add(productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(404, "getProduct", "productId", productId)));
            }
        }
        userRepository.save(user);
        return new ApiResponse<>(status.equals("like") ? tr == 0 ? "Sevimlilarga qo'shildi" : "Sevimlilardan olib tashlandi" : tr == 0 ? "Savatchaga saqlandi" : "Savatchadan olib tashlandi", true, 200);
    }

    public List<Product> getProductByCategoryName(String name) {
        return productRepository.findAllByCategoryName(name);
    }
}
