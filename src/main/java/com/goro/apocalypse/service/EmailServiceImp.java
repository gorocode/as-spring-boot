package com.goro.apocalypse.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService{
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String from, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        text = "From: " + from + "\n" + text;

        helper.setTo("thegorocode@gmail.com");
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setFrom("thegorocode@gmail.com");

        mailSender.send(message);
    }
}
