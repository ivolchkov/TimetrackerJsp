package project.service.mapper;

import org.junit.Test;
import project.domain.backlog.Backlog;
import project.domain.user.User;
import project.entity.backlog.BacklogEntity;
import project.entity.user.Role;
import project.entity.user.UserEntity;

import static org.junit.Assert.*;

public class UserMapperTest {
    private static final Backlog BACKLOG = new Backlog(1);

    private static final BacklogEntity BACKLOG_ENTITY = BacklogEntity.builder().withId(1).build();

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

    private static final UserEntity ENTITY_WITH_BACKLOG = UserEntity.builder()
            .withId(1)
            .withName("Test")
            .withName("Test")
            .withSurname("Test")
            .withEmail("Test")
            .withPassword("Test")
            .withRole(Role.ADMIN)
            .withBacklog(BACKLOG_ENTITY)
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

    @Test
    public void mapUserToUserEntityShouldMapToEntityWithUser() {
        UserEntity actual = MAPPER.mapUserToUserEntity(DOMAIN, BACKLOG);

        assertEquals(ENTITY_WITH_BACKLOG, actual);
    }
}