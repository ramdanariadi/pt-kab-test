package id.grocery.tunas.product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public interface ProductRepository extends CrudRepository<Product, UUID> {

    Product findProductById(UUID id);

    @Modifying
    @Transactional
    @Query("update Product p set p.name = :name, p.price = :price, p.description = :description, p.stock = :stock where p.id = :id")
    int updateProduct(@Param("id") UUID id,
                      @Param("name") String name,
                      @Param("price") BigDecimal price,
                      @Param(("stock")) Integer stock,
                      @Param("description") String description);
}
