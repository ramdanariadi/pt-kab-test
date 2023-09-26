package id.grocery.tunas.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class GetCustomerByIdDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Customer{
        private UUID id;
        private String username;
        private String address;
        private String phone;
    }
}
