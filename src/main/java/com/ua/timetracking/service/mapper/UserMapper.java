package com.ua.timetracking.service.mapper;

import com.ua.timetracking.domain.User;
import com.ua.timetracking.entity.UserEntity;

public class UserMapper {
    public UserEntity mapUserToUserEntity(User domain) {
        return UserEntity.builder()
                .withName(domain.getName())
                .withSurname(domain.getSurname())
                .withEmail(domain.getEmail())
                .withPassword(domain.getPassword())
                .withRole(domain.getRole())
                .build();
    }

    public User mapUserEntityToUser(UserEntity entity) {
        return User.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withSurname(entity.getSurname())
                .withEmail(entity.getEmail())
                .withRole(entity.getRole())
                .build();
    }
}
