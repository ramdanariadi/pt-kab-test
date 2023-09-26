package id.grocery.tunas.product;

import id.grocery.tunas.product.dto.AddAndUpdateProductDTO;
import id.grocery.tunas.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable UUID id){
        ProductDTO productById = productService.findProductById(id);
        return ResponseEntity.ok(productById);
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody AddAndUpdateProductDTO requestBody){
        productService.saveProduct(requestBody);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @RequestBody AddAndUpdateProductDTO requestBody){
        productService.updateProduct(id, requestBody);
        return ResponseEntity.ok().build();
    }

}
