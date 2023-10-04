package it.revo.onemilioncourse.entity;

import it.revo.onemilioncourse.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Location extends AbsEntity {
    private String longitude;
    private String latitude;
}
