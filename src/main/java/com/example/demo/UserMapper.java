package com.example.demo;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "email", target = "emailAddress"),
            @Mapping(source = "role", target = "roleName")
    })
    UserMsDTO userToUserMsDTO(User user);

    List<UserMsDTO> usersToUserMsDTOList(List<User> userList);

}
