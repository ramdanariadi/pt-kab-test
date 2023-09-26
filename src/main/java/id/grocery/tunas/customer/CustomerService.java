package id.grocery.tunas.customer;

import com.google.common.base.Strings;
import id.grocery.tunas.customer.dto.AddAndUpdateCustomerDTO;
import id.grocery.tunas.customer.dto.GetCustomerByIdDTO;
import id.grocery.tunas.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void saveUser(AddAndUpdateCustomerDTO.Request request){
        if(Strings.isNullOrEmpty(request.getUsername())){
            throw new ApiRequestException("BAD_REQUEST");
        }

        Customer customerByUserName = customerRepository.findUserByCustomer(request.getUsername());

        if(null != customerByUserName){
            throw new ApiRequestException("USERNAME_TAKEN");
        }

        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        customerRepository.save(customer);
    }

    public void updateUser(UUID id, AddAndUpdateCustomerDTO.Request request){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()){
            throw new ApiRequestException("CUSTOMER_NOT_FOUND");
        }

        Customer customer = customerOptional.get();
        Customer customerByUserName = customerRepository.findUserByCustomer(request.getUsername());

        if(null != customerByUserName && customer.getId().compareTo(customerByUserName.getId()) != 0){
            throw new ApiRequestException("USERNAME_TAKEN");
        }

        customer.setUsername(request.getUsername());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        customerRepository.save(customer);
    }

    public GetCustomerByIdDTO.Customer findById(UUID id) {
        Optional<Customer> user = customerRepository.findById(id);
        if(!user.isPresent()){
            throw new ApiRequestException(ApiRequestException.BAD_REQUEST);
        }
        Customer customer = user.get();
        return new GetCustomerByIdDTO.Customer(customer.getId(), customer.getUsername(), customer.getAddress(), customer.getPhone());
    }
}
