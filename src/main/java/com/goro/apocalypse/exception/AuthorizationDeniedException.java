package com.goro.apocalypse.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationDeniedException extends RuntimeException {
    private String messageEn;
    private String messageEs;

    public AuthorizationDeniedException (String messageEn, String messageEs) {
        super(messageEn);
        this.messageEn = messageEn;
        this.messageEs = messageEs;
    }
}
