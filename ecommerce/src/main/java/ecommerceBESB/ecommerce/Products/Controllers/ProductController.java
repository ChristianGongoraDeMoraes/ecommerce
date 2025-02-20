package ecommerceBESB.ecommerce.Products.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ecommerceBESB.ecommerce.Errors.Exceptions.ProductNotFoundException;
import ecommerceBESB.ecommerce.Products.Product;
import ecommerceBESB.ecommerce.Products.Requests.ProductSaveRequest;
import ecommerceBESB.ecommerce.Products.Requests.ProductUpdateRequest;
import ecommerceBESB.ecommerce.Products.Services.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/Api/Product")
@Transactional
@Validated
public class ProductController {
    private final ProductService productService;
    
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(name));
    }
    

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductSaveRequest prodReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(prodReq));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateUser(@PathVariable UUID id, @Valid @RequestBody ProductUpdateRequest prodReq) throws ProductNotFoundException{
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(id, prodReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }
}
