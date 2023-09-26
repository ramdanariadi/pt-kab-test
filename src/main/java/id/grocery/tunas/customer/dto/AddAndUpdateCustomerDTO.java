package id.grocery.tunas.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class AddAndUpdateCustomerDTO {
    @AllArgsConstructor
    @Setter
    @Getter
    public static class Request{
        private String username;
        private String address;
        private String phone;
    }

}
