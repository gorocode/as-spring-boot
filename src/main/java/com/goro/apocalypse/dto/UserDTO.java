package com.goro.apocalypse.dto;

import com.goro.apocalypse.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String profilePicture;
    private String provider;
    private Role role;
}
