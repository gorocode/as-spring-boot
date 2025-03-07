package com.goro.apocalypse.mapper;

import com.goro.apocalypse.dto.PlayerDTO;
import com.goro.apocalypse.entity.PlayerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDTO toDTO(PlayerEntity player);

    List<PlayerDTO> toDTO(List<PlayerEntity> player);
}
