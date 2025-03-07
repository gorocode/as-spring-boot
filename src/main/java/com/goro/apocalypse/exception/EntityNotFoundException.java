package com.goro.apocalypse.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException{
    private String messageEn;
    private String messageEs;

    public EntityNotFoundException (String messageEn, String messageEs) {
        super(messageEn);
        this.messageEn = messageEn;
        this.messageEs = messageEs;
    }
}
