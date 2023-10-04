package it.revo.onemilioncourse.entity;

import it.revo.onemilioncourse.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BuyProduct extends AbsEntity {
    @ManyToMany
    private List<Product> product;
    private int size;
    private double sum;
    @ManyToMany
    private List<Location> locations;
}
