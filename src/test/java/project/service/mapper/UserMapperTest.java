package project.service.mapper;

import org.junit.Test;
import project.domain.User;
import project.entity.Role;
import project.entity.UserEntity;

import static org.junit.Assert.*;

public class UserMapperTest {
    private static final UserEntity ENTITY = UserEntity.builder()
            .withName("Test")
            .withSurname("Test")
            .withEmail("Test")
            .withPassword("Test")
            .withRole(Role.ADMIN)
            .build();

    private static final UserEntity ENTITY_WITH_ID = UserEntity.builder()
            .withId(1)
            .withName("Test")
            .withSurname("Test")
            .withEmail("Test")
            .withPassword("Test")
            .withRole(Role.ADMIN)
            .build();

    private static final User DOMAIN = User.builder()
            .withId(1)
            .withName("Test")
            .withSurname("Test")
            .withEmail("Test")
            .withPassword("Test")
            .withRole(Role.ADMIN)
            .build();

    private static final UserMapper MAPPER = new UserMapper();

    @Test
    public void mapUserToUserEntityShouldMapToEntity() {
        UserEntity actual = MAPPER.mapUserToUserEntity(DOMAIN);

        assertEquals(ENTITY, actual);
    }

    @Test
    public void mapUserEntityToUserShouldMapToDomain() {
        User actual = MAPPER.mapUserEntityToUser(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }
}