package it.revo.onemilioncourse.entity;

import it.revo.onemilioncourse.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product extends AbsEntity {
    private String name;
    private double price;
    private String description;
    private UUID photoId;
    @ManyToOne(optional = false)
    private Category category;
}
