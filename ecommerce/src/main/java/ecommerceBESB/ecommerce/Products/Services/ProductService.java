package ecommerceBESB.ecommerce.Products.Services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ecommerceBESB.ecommerce.Errors.Exceptions.ProductNotFoundException;
import ecommerceBESB.ecommerce.Products.Product;
import ecommerceBESB.ecommerce.Products.Repositories.ProductRepository;
import ecommerceBESB.ecommerce.Products.Requests.ProductSaveRequest;
import ecommerceBESB.ecommerce.Products.Requests.ProductUpdateRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAllProducts();
    }

    public Product getProductByName(String name){
        return productRepository.findProductByName(name).orElseThrow(() -> new ProductNotFoundException("Product Not found!"));
    }
    public Product getProductById(String id){
        return productRepository.findProductById(UUID.fromString(id)).orElseThrow(() -> new ProductNotFoundException("Product Not found!"));
    }

    public Product saveProduct(ProductSaveRequest prodReq){
        Product product = Product.builder()
            .name(prodReq.name())
            .price(prodReq.price())
            .build();
        return productRepository.save(product);
    }


    public Product updateProduct(UUID id, ProductUpdateRequest prodReq) throws ProductNotFoundException{
        Product product = productRepository.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Product Not found!"));
        product = Product.builder()
            .id(product.getId())
            .name(prodReq.name())
            .price(prodReq.price())
            .build();
        return productRepository.save(product);
    }

    public String deleteProduct(UUID id) throws ProductNotFoundException{
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return "Product Deleted";
        }else{
            throw new ProductNotFoundException("Product Not found!");
        }
    }
}

