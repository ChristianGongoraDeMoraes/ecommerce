package ecommerceBESB.ecommerce.User.Controllers;

import ecommerceBESB.ecommerce.Errors.Exceptions.UserNotFoundException;
import ecommerceBESB.ecommerce.User.Image;
import ecommerceBESB.ecommerce.User.Repositories.ImageRepository;
import ecommerceBESB.ecommerce.User.Repositories.UserRepository;
import ecommerceBESB.ecommerce.User.ImageUtility;
import ecommerceBESB.ecommerce.User.User;
import ecommerceBESB.ecommerce.User.Requests.ImageUploadResponse;
import ecommerceBESB.ecommerce.User.Requests.RequestUserEmail;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserRepository userRepository;

    private RequestUserEmail convertUserEmail(String userEmail) throws JsonProcessingException{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(userEmail , RequestUserEmail.class);
    }

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestPart("image") MultipartFile file, @RequestPart("user") String userEmail)
            throws IOException , UserNotFoundException{
        
        RequestUserEmail userReqEmail = convertUserEmail(userEmail);
        User user = userRepository.findUserByEmail(userReqEmail.getUserEmail()).orElseThrow(() -> new UserNotFoundException("User Not found!"));

                
        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes()))
                .userImage(user)
                .build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping("/get/image/info/{name}")
    public Image getImageDetails(@PathVariable String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }
    
    

    @GetMapping("/get/image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }

    @GetMapping("/get/image/user/{email}")
    public ResponseEntity<byte[]> getImageByEmail(@PathVariable String email) throws UserNotFoundException {

        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not found!"));

        final Image dbImage = imageRepository.findByUserId(user.getId()).orElseThrow(() -> new UserNotFoundException("Image Not found!"));

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.getType()))
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }
}