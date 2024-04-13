package com.example.lmsservice.mapper;

import com.example.lmsservice.dto.SaveUserDTO;
import com.example.lmsservice.models.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserInfo mapToUserInfo(SaveUserDTO saveUserDTO);
}
