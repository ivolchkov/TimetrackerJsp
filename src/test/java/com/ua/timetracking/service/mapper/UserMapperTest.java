package com.ua.timetracking.service.mapper;

import org.junit.Test;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.entity.Role;
import com.ua.timetracking.entity.UserEntity;

import static org.junit.Assert.*;

public class UserMapperTest {
    private static final UserEntity USER_ENTITY = getUserEntity();

    private static final UserEntity ENTITY_WITH_ID = getEntity();

    private static final User DOMAIN = getUser();

    private final UserMapper userMapper = new UserMapper();

    @Test
    public void mapUserToUserEntityShouldMapToEntity() {
        UserEntity actual = userMapper.mapUserToUserEntity(DOMAIN);

        assertEquals(USER_ENTITY, actual);
    }

    @Test
    public void mapUserEntityToUserShouldMapToDomain() {
        User actual = userMapper.mapUserEntityToUser(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }

    private static UserEntity getEntity() {
        return UserEntity.builder()
                .withId(1)
                .withName("Test")
                .withSurname("Test")
                .withEmail("Test")
                .withPassword("Test")
                .withRole(Role.ADMIN)
                .build();
    }

    private static UserEntity getUserEntity() {
        return UserEntity.builder()
                .withName("Test")
                .withSurname("Test")
                .withEmail("Test")
                .withRole(Role.ADMIN)
                .build();
    }

    private static User getUser() {
        return User.builder()
                .withId(1)
                .withName("Test")
                .withSurname("Test")
                .withEmail("Test")
                .withRole(Role.ADMIN)
                .build();
    }
}