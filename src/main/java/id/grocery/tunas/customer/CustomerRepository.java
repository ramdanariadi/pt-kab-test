package id.grocery.tunas.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    @Query("SELECT c FROM Customer c WHERE c.username = :username")
    Customer findUserByCustomer(@Param("username") String username);
}
