package ecommerceBESB.ecommerce.User.Controllers;

import ecommerceBESB.ecommerce.User.Image;
import ecommerceBESB.ecommerce.User.Repositories.ImageRepository;
import ecommerceBESB.ecommerce.User.ImageUtility;

import ecommerceBESB.ecommerce.User.Requests.ImageUploadResponse;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ecommerceBESB.ecommerce.User.ImageUtility.compressImage(file.getBytes())).build());
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
}