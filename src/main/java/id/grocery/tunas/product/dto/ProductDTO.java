package id.grocery.tunas.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class ProductDTO{
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stock;
}
