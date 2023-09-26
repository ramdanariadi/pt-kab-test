package id.grocery.tunas.customer;

import id.grocery.tunas.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Data
public class Customer extends BaseModel {
    @Column(name = "username", unique = true, length = 100)
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;
}
