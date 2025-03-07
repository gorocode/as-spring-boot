package com.goro.apocalypse.service;

import com.goro.apocalypse.dto.PlayerDTO;
import com.goro.apocalypse.entity.EventEntity;
import com.goro.apocalypse.entity.Role;
import com.goro.apocalypse.entity.UserEntity;
import com.goro.apocalypse.exception.AuthorizationDeniedException;
import com.goro.apocalypse.exception.EntityNotFoundException;
import com.goro.apocalypse.mapper.PlayerMapper;
import com.goro.apocalypse.repository.EventRepository;
import com.goro.apocalypse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goro.apocalypse.entity.PlayerEntity;
import com.goro.apocalypse.repository.PlayerRepository;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImp implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public PlayerDTO createPlayer(String name, Long userId) {
        List<PlayerEntity> userPlayers = playerRepository.findByUserId(userId);
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found!", "¡Usuario no encontrado"));
        if (userPlayers.size() >= 8 && user.getRole() != Role.GUEST) {
            throw new AuthorizationDeniedException("You can't have more than 8 players!", "¡No puedes tener más de 8 jugadores!");
        }

        PlayerEntity player = new PlayerEntity();
        player.setName(name);
        player.setHealth(100);
        player.setFood(50);
        player.setMoral(75);
        player.setSurvivedDays(1);
        player.setUser(user);
        return playerMapper.toDTO(playerRepository.save(player));
    }

    @Override
    public List<PlayerDTO> getUserPlayers(Long userId) {
        return playerMapper.toDTO(playerRepository.findByUserId(userId));
    }

    @Override
    public PlayerDTO playerAction(Long playerId, Long eventId, int option) {
        PlayerEntity player = playerRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player not found!", "¡Jugador no encontrado"));
        EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found!", "¡Evento no encontrado"));

        if (option == 1) {
            int newHealth = player.getHealth() + event.getHealthEffect1();
            player.setHealth(Math.min(Math.max(newHealth, 0), 100));
            int newFood = player.getFood() + event.getFoodEffect1();
            player.setFood(Math.min(Math.max(newFood, 0), 100));
            int newMoral = player.getMoral() + event.getMoralEffect1();
            player.setMoral(Math.min(Math.max(newMoral, 0), 100));
        } else {
            int newHealth = player.getHealth() + event.getHealthEffect2();
            player.setHealth(Math.min(Math.max(newHealth, 0), 100));
            int newFood = player.getFood() + event.getFoodEffect2();
            player.setFood(Math.min(Math.max(newFood, 0), 100));
            int newMoral = player.getMoral() + event.getMoralEffect2();
            player.setMoral(Math.min(Math.max(newMoral, 0), 100));
        }

        player.setSurvivedDays(player.getSurvivedDays() + 1);
        return playerMapper.toDTO(playerRepository.save(player));
    }

    @Override
    public PlayerDTO updatePlayer(PlayerDTO player) {
        PlayerEntity playerEntity = playerRepository.findById(player.getId()).orElseThrow(() -> new EntityNotFoundException("Player not found!", "¡Jugador no encontrado"));
        if (player.isFinished()) {
            playerEntity.setFinished(true);
        }
        if (player.getHealth() <= 0 || player.getFood() <= 0) {
            playerEntity.setFinished(true);
        }
        playerEntity.setHealth(player.getHealth());
        playerEntity.setFood(player.getFood());
        playerEntity.setMoral(player.getMoral());
        playerEntity.setSurvivedDays(player.getSurvivedDays());
        return playerMapper.toDTO(playerRepository.save(playerEntity));
    }

    @Override
    public void deletePlayer(Long playerId, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found!", "¡Usuario no encontrado"));
        PlayerEntity player = playerRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player not found!", "¡Jugador no encontrado"));
        if (Objects.equals(player.getUser().getId(), user.getId())) {
            playerRepository.deleteById(playerId);
        }
    }
}
