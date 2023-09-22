package it.revo.onemilioncourse.payload;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private double price;
    private String description;
    private UUID photoId;
    private Integer categoryId;
}
