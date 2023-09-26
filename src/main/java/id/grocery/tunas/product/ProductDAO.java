package id.grocery.tunas.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductDAO {

    @PersistenceContext
    private EntityManager em;

    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private Optional<String> schema;

    public List<Product> getProductById(UUID id, boolean lock){
        Query nativeQuery = em.createQuery("SELECT p FROM Product p " +
                "WHERE id = :id AND deletedAt IS NULL", Product.class);
        if(lock){
            nativeQuery.setLockMode(LockModeType.PESSIMISTIC_READ);
        }
        nativeQuery.setParameter("id", id);
        return nativeQuery.getResultList();
    }
}
