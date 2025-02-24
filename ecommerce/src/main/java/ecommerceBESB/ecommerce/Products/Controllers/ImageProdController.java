package ecommerceBESB.ecommerce.Products.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerceBESB.ecommerce.Errors.Exceptions.ProductNotFoundException;
import ecommerceBESB.ecommerce.Errors.Exceptions.UserNotFoundException;
import ecommerceBESB.ecommerce.Products.ImageProd;
import ecommerceBESB.ecommerce.Products.ImageProdUtility;
import ecommerceBESB.ecommerce.Products.Product;
import ecommerceBESB.ecommerce.Products.Repositories.ImageProdRepository;
import ecommerceBESB.ecommerce.Products.Repositories.ProductRepository;
import ecommerceBESB.ecommerce.Products.Requests.ImageProdUploadResponse;
import ecommerceBESB.ecommerce.Products.Requests.RequestProductName;
import ecommerceBESB.ecommerce.User.Image;
import ecommerceBESB.ecommerce.User.ImageUtility;
import ecommerceBESB.ecommerce.User.User;
import ecommerceBESB.ecommerce.User.Requests.RequestUserEmail;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Transactional
public class ImageProdController {

    @Autowired
    ImageProdRepository imageRepository;

    @Autowired
    ProductRepository productRepository;

        private RequestProductName convertProductName(String productName) throws JsonProcessingException{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(productName , RequestProductName.class);
        }

    @PostMapping("/upload/image")
    public ResponseEntity<ImageProdUploadResponse> uplaodImage(@RequestPart("image") MultipartFile file, @RequestPart("product") String productName)
            throws IOException , ProductNotFoundException{
        

        RequestProductName Dto = convertProductName(productName);
        Product product = productRepository.findProductByName(Dto.getProductName()).orElseThrow(() -> new ProductNotFoundException("Product Not found!"));
        
        imageRepository.save(ImageProd.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageProdUtility.compressImage(file.getBytes()))
                .productImage(product)
                .build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageProdUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping("/get/image/info/{name}")
    public ImageProd getImageDetails(@PathVariable String name) throws IOException {

        final Optional<ImageProd> dbImage = imageRepository.findProductImageByName(name);

        return ImageProd.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageProdUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping("/get/image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {

        final Optional<ImageProd> dbImage = imageRepository.findProductImageByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageProdUtility.decompressImage(dbImage.get().getImage()));
    }

    @GetMapping("/get/image/product/{name}")
    public ResponseEntity<byte[]> getImageByProductName(@PathVariable String name) throws ProductNotFoundException {

        Product product = productRepository.findProductByName(name).orElseThrow(() -> new ProductNotFoundException("Product Not found!"));

        final ImageProd dbImage = imageRepository.findImageByProductId(product.getId()).orElseThrow(() -> new ProductNotFoundException("Image Not found!"));

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.getType()))
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }
}