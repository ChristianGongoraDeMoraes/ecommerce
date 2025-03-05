package ecommerceBESB.ecommerce.Mail.service;


import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ecommerceBESB.ecommerce.Mail.dtos.EmailDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviaEmail(EmailDto emailDto) {
       try {
           MimeMessage message = javaMailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true);

           helper.setFrom("noreply@gmail.com");
           helper.setSubject("Ecommerce Test");
           helper.setTo(emailDto.email());
           helper.setText("TEXTO EMAIL");

        /*
           String template  = carregaTemplateEmail();

           template = template.replace("#{nome}", emailDto.nome());
           helper.setText(template, true);
        */

           javaMailSender.send(message);
       } catch (Exception exception) {
           System.out.println("Falha ao enviar o email");
       }
    }

    public String carregaTemplateEmail() throws IOException {
        ClassPathResource resource = new ClassPathResource("template-email.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
