package project.service.mapper;

import project.domain.backlog.Backlog;
import project.domain.user.User;
import project.entity.backlog.BacklogEntity;
import project.entity.user.UserEntity;

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

    public UserEntity mapUserToUserEntity(User domain, Backlog backlog) {
        return UserEntity.builder()
                .withId(domain.getId())
                .withName(domain.getName())
                .withSurname(domain.getSurname())
                .withEmail(domain.getEmail())
                .withPassword(domain.getPassword())
                .withRole(domain.getRole())
                .withBacklog(BacklogEntity.builder()
                        .withId(backlog.getId())
                        .build())
                .build();
    }

    public User mapUserEntityToUser(UserEntity entity) {
        return User.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withSurname(entity.getSurname())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }
}
