package id.grocery.tunas.product;

import com.google.common.base.Strings;
import id.grocery.tunas.exception.ApiRequestException;
import id.grocery.tunas.product.dto.AddAndUpdateProductDTO;
import id.grocery.tunas.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO findProductById(UUID id){
        Product productById = productRepository.findProductById(id);
        if(null == productById){
            throw new ApiRequestException("INVALID_PRODUCT");
        }
        ProductDTO productDTO = new ProductDTO(productById.getId(), productById.getName(), productById.getPrice(), productById.getDescription(), productById.getStock());
        return productDTO;
    }

    public List<ProductDTO> getAllProduct(){
        List<ProductDTO> result = new ArrayList<>();
        Iterable<Product> all = productRepository.findAll();
        all.forEach(product -> {
            result.add(new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getStock()));
        });
        return result;
    }

    public void saveProduct(AddAndUpdateProductDTO requestBody){
        if(null == requestBody.getPrice() || requestBody.getPrice().compareTo(BigDecimal.ONE) < 0 ||
            null == requestBody.getStock() || requestBody.getStock().compareTo(1) < 0 ||
            Strings.isNullOrEmpty(requestBody.getName())){
            throw new ApiRequestException(ApiRequestException.BAD_REQUEST);
        }

        Product product = new Product();
        product.setName(requestBody.getName());
        product.setDescription(requestBody.getDescription());
        product.setPrice(requestBody.getPrice());
        product.setStock(requestBody.getStock());
        productRepository.save(product);
    }

    public void updateProduct(UUID id,AddAndUpdateProductDTO product){
        if(Strings.isNullOrEmpty(product.getName())){
            throw new ApiRequestException("PRODUCT_NAME_CANNOT_EMPTY");
        }
        productRepository.updateProduct(id,product.getName(), product.getPrice(), product.getStock(), product.getDescription());
    }

}
