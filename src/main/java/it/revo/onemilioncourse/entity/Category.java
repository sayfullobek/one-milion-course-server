package it.revo.onemilioncourse.entity;

import it.revo.onemilioncourse.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
public class Category extends AbsNameEntity {
}
