package id.grocery.tunas.product.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class AddAndUpdateProductDTO {
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stock;
}
