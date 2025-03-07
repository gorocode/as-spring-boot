package com.goro.apocalypse.controller;

import com.goro.apocalypse.dto.ResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import com.goro.apocalypse.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/contact")
    public ResponseEntity<ResponseDTO> sendEmail(@RequestBody Map<String, String> body) {
        try {
            emailService.sendEmail(body.get("email"), "AS Contact Form from " + body.get("name"), body.get("message"));
            ResponseDTO success = new ResponseDTO();
            success.setMessageEn("Contact form sent successfully!");
            success.setMessageEs("¡Formulario de contacto enviado!");
            return ResponseEntity.ok(success);
        } catch (MessagingException e) {
            ResponseDTO error = new ResponseDTO();
            error.setMessageEn("Error while sending contact form!");
            error.setMessageEs("¡Error al enviar el formulario de contacto");
            return ResponseEntity.status(HttpStatus.CREATED).body(error);
        }
    }
}
