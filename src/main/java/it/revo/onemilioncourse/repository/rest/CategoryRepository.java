package it.revo.onemilioncourse.repository.rest;

import it.revo.onemilioncourse.entity.Category;
import it.revo.onemilioncourse.projection.CustomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "category", excerptProjection = CustomCategory.class, collectionResourceRel = "list")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
