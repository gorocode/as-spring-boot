package com.goro.apocalypse.mapper;

import com.goro.apocalypse.dto.UserDTO;
import com.goro.apocalypse.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(UserEntity user);
}
