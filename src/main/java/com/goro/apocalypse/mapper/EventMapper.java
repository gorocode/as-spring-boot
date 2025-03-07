package com.goro.apocalypse.mapper;

import com.goro.apocalypse.dto.EventDTO;
import com.goro.apocalypse.entity.EventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDTO(EventEntity event);
}
