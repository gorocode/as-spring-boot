package com.goro.apocalypse.service;

import com.goro.apocalypse.dto.UserDTO;
import com.goro.apocalypse.entity.UserEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {
    UserEntity processOAuthPostLogin(OAuth2User oAuth2User);
    UserDTO getUserByEmail(String email);
    UserDTO updateUser(UserDTO user, String email);
    Boolean isEmailInUse(String email);
}
