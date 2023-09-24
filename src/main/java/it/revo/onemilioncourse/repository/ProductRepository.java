package it.revo.onemilioncourse.repository;

import it.revo.onemilioncourse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(Integer category_id);

    List<Product> findAllByCategoryName(String category_name);

    @Query(value = "select * from products where name like %?1%", nativeQuery = true)
    List<Product> searchByName(String name);
}
