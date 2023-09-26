package id.grocery.tunas.order;

import id.grocery.tunas.order.dto.AddOrderDTO;
import javax.servlet.http.HttpServletRequest;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody AddOrderDTO.Request body){
        orderService.createOrder(body);
        return ResponseEntity.ok().build();
    }
}
