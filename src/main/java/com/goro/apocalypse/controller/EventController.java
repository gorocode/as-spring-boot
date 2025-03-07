package com.goro.apocalypse.controller;

import com.goro.apocalypse.dto.EventDTO;
import com.goro.apocalypse.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/random")
    public ResponseEntity<EventDTO> getEvent() {
        EventDTO event = eventService.getRandomEvent();
        return ResponseEntity.ok(event);
    }
}
