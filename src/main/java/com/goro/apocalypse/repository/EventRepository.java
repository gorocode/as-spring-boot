package com.goro.apocalypse.repository;

import com.goro.apocalypse.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @Query(value = "SELECT * FROM event_entity ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    EventEntity getRandomEvent();
}
