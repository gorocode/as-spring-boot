package com.goro.apocalypse.service;

import com.goro.apocalypse.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(String name, Long userId);

    List<PlayerDTO> getUserPlayers(Long userId);

    PlayerDTO playerAction(Long playerId, Long eventId, int option);

    PlayerDTO updatePlayer(PlayerDTO player);

    void deletePlayer(Long playerId, String email);
}
