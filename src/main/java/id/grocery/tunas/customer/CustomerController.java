package id.grocery.tunas.customer;

import id.grocery.tunas.customer.dto.AddAndUpdateCustomerDTO;
import id.grocery.tunas.customer.dto.GetCustomerByIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody AddAndUpdateCustomerDTO.Request request){
        customerService.saveUser(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody AddAndUpdateCustomerDTO.Request request){
        customerService.updateUser(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerByIdDTO.Customer> getById(@PathVariable("id") UUID id){
        GetCustomerByIdDTO.Customer customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping()
    public ResponseEntity<List<GetCustomerByIdDTO.Customer>> getAllCustomer(){
        List<GetCustomerByIdDTO.Customer> all = customerService.getAll();
        return ResponseEntity.ok(all);
    }
}
