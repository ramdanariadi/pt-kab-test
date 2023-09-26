package id.grocery.tunas.order.dto;

import lombok.*;
import java.util.UUID;

public class AddOrderDTO {
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request{
        private UUID customerId;
        private UUID productId;
        private int qty;
    }
}
