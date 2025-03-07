package com.goro.apocalypse.controller;

import com.goro.apocalypse.dto.UserDTO;
import com.goro.apocalypse.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> user(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal != null ? principal.getAttribute("email") : null;
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping
    public ResponseEntity<UserDTO> userUpdate(@RequestBody @Valid UserDTO user, @AuthenticationPrincipal OAuth2User principal) {
        String email = principal != null ? principal.getAttribute("email") : null;
        if (email == null) return null;

        return ResponseEntity.ok(userService.updateUser(user, email));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/email-in-use/{email}")
    public ResponseEntity<Boolean> emailInUse(@PathVariable String email) {
        return ResponseEntity.ok(userService.isEmailInUse(email));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.logout();
        return ResponseEntity.noContent().build();
    }

}
