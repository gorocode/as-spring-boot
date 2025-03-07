package com.goro.apocalypse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private Long id;
    private String name;
    private int health;
    private int food;
    private int moral;
    private int survivedDays;
    private boolean finished = false;
}
