package id.grocery.tunas.product;

import id.grocery.tunas.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_product_name", columnList = "name")
})
@Data
@NoArgsConstructor
public class Product extends BaseModel {
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "numeric(19,2) default 0")
    private BigDecimal price;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}
