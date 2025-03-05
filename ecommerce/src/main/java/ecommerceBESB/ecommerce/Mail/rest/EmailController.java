package ecommerceBESB.ecommerce.Mail.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ecommerceBESB.ecommerce.Mail.dtos.EmailDto;
import ecommerceBESB.ecommerce.Mail.service.EmailService;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void enviarEmail(@RequestBody EmailDto emailDto) {
        emailService.enviaEmail(emailDto);
    }
}
