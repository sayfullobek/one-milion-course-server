package it.revo.onemilioncourse.repository;

import it.revo.onemilioncourse.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
