package com.goro.apocalypse.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String from, String subject, String text) throws MessagingException;
}
