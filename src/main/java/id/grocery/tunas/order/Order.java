package id.grocery.tunas.order;

import id.grocery.tunas.base.BaseModel;
import id.grocery.tunas.customer.Customer;
import id.grocery.tunas.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseModel {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;

    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "amount", nullable = false, columnDefinition = "numeric(19,2) default 0" )
    private BigDecimal amount;
}
