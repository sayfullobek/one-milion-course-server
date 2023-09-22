package it.revo.onemilioncourse.repository;

import it.revo.onemilioncourse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(Integer category_id);

    List<Product> findAllByCategoryName(String category_name);
}
