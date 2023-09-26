package id.grocery.tunas.order;

import id.grocery.tunas.customer.Customer;
import id.grocery.tunas.customer.CustomerRepository;
import id.grocery.tunas.exception.ApiRequestException;
import id.grocery.tunas.order.dto.AddOrderDTO;
import id.grocery.tunas.product.Product;
import id.grocery.tunas.product.ProductDAO;
import id.grocery.tunas.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductDAO productDAO;

    @Transactional
    public void createOrder(AddOrderDTO.Request body){
        Optional<Customer> customerOptional = customerRepository.findById(body.getCustomerId());

        if(!customerOptional.isPresent()){
            throw new ApiRequestException(ApiRequestException.BAD_REQUEST);
        }

        List<Product> productById = productDAO.getProductById(body.getProductId(), true);
        if(productById.isEmpty()){
            throw new ApiRequestException("INVALID_PRODUCT");
        }

        Product product = productById.get(0);

        if(product.getStock() < body.getQty()){
            throw new ApiRequestException("INVALID_STOCK");
        }

        Customer customer = customerOptional.get();
        Order order = new Order();
        order.setCustomer(customer);
        order.setCustomerName(customer.getUsername());
        order.setProduct(product);
        order.setQty(body.getQty());
        order.setAmount(product.getPrice().multiply(BigDecimal.valueOf(body.getQty())));
        orderRepository.save(order);
        product.setStock(product.getStock()-body.getQty());
        productRepository.save(product);
    }
}
