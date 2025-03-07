package com.goro.apocalypse.service;

import com.goro.apocalypse.dto.EventDTO;
import com.goro.apocalypse.mapper.EventMapper;
import com.goro.apocalypse.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImp implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Override
    public EventDTO getRandomEvent() {
        return eventMapper.toDTO(eventRepository.getRandomEvent());
    }
}
