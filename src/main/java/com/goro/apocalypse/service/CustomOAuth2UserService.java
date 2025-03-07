package com.goro.apocalypse.service;

import com.goro.apocalypse.entity.CustomOAuth2User;
import com.goro.apocalypse.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        UserEntity user = userService.processOAuthPostLogin(oAuth2User);

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

        return new CustomOAuth2User(oAuth2User, authorities);
    }
}
