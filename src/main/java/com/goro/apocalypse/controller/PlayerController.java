package com.goro.apocalypse.controller;

import com.goro.apocalypse.dto.PlayerDTO;
import com.goro.apocalypse.dto.UserDTO;
import com.goro.apocalypse.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/new")
    public ResponseEntity<PlayerDTO> newPlayer(@RequestBody Map<String, String> body) {
        PlayerDTO player = playerService.createPlayer(body.get("name"), Long.valueOf(body.get("userId")));
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlayerDTO>> getUserPlayers(@PathVariable Long userId) {
        return ResponseEntity.ok(playerService.getUserPlayers(userId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody @Valid PlayerDTO player) {
        return ResponseEntity.ok(playerService.updatePlayer(player));
    }


    @PostMapping("/action")
    public ResponseEntity<PlayerDTO> actionApply(@RequestBody Map<String, Integer> body) {
        Long playerId = body.get("playerId").longValue();
        Long eventId = body.get("eventId").longValue();
        int option = body.get("option");

        return ResponseEntity.ok(playerService.playerAction(playerId, eventId, option));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @DeleteMapping("/{playerId}")
    public ResponseEntity<Map<String, String>> deletePlayer(@PathVariable Long playerId, @AuthenticationPrincipal OAuth2User principal) {
        String email = principal != null ? principal.getAttribute("email") : null;
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Can't delete this player"));
        }
        playerService.deletePlayer(playerId, email);
        return ResponseEntity.ok(Map.of("message", "Player deleted successfully"));
    }

}
