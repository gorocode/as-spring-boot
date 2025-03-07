package com.goro.apocalypse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goro.apocalypse.entity.PlayerEntity;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    List<PlayerEntity> findByUserId(Long userId);
}
