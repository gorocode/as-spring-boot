package com.goro.apocalypse.service;

import com.goro.apocalypse.dto.UserDTO;
import com.goro.apocalypse.entity.Role;
import com.goro.apocalypse.entity.UserEntity;
import com.goro.apocalypse.exception.AuthorizationDeniedException;
import com.goro.apocalypse.exception.EntityNotFoundException;
import com.goro.apocalypse.mapper.UserMapper;
import com.goro.apocalypse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserEntity processOAuthPostLogin(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setName(oAuth2User.getAttribute("name"));
        newUser.setUsername(email.split("@")[0]);
        newUser.setProfilePicture(oAuth2User.getAttribute("picture"));
        newUser.setProvider("GOOGLE");
        newUser.setRole(Role.USER);

        return userRepository.save(newUser);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO user = null;
        if (email != null) {
            UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
            if (userEntity != null) {
                return userMapper.toDTO(userEntity);
            }
        }

        user = new UserDTO();
        user.setId(0L);
        user.setUsername("Guest");
        user.setRole(Role.GUEST);
        return user;
    }

    @Override
    public UserDTO updateUser(UserDTO user, String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found!", "¡Usuario no encontrado"));

        if (Objects.equals(userEntity.getId(), user.getId())) {
            userEntity.setName(user.getName());
            userEntity.setUsername(user.getUsername());
            userEntity.setEmail(user.getEmail());
            userEntity.setProfilePicture(user.getProfilePicture());
            return userMapper.toDTO(userRepository.save(userEntity));
        } else {
            throw new AuthorizationDeniedException("You are not allow to update this user!", "¡No tienes permitido actualizar este usuario!");
        }
    }

    @Override
    public Boolean isEmailInUse(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
