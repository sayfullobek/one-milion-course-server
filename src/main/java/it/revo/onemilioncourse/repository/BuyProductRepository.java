package it.revo.onemilioncourse.repository;

import it.revo.onemilioncourse.entity.BuyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuyProductRepository extends JpaRepository<BuyProduct, UUID> {
}
