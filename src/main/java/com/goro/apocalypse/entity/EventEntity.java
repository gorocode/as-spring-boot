package com.goro.apocalypse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriptionEn;
    private String descriptionEs;

    private String option1En;
    private String option2En;
    private String option1Es;
    private String option2Es;

    private String action1En;
    private String action2En;
    private String action1Es;
    private String action2Es;

    private int healthEffect1;
    private int foodEffect1;
    private int moralEffect1;
    private int healthEffect2;
    private int foodEffect2;
    private int moralEffect2;
    private String background;
    private String sprite;
}
