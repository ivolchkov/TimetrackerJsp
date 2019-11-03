package project.context;

import project.domain.user.User;
import project.repository.backlogDao.BacklogDao;
import project.repository.backlogDao.BacklogDaoImpl;
import project.repository.connector.WrapperConnector;
import project.repository.goalDao.GoalDao;
import project.repository.goalDao.GoalDaoImpl;
import project.repository.sprintDao.SprintDao;
import project.repository.sprintDao.SprintDaoImpl;
import project.repository.storyDao.StoryDao;
import project.repository.storyDao.StoryDaoImpl;
import project.repository.userDao.UserDao;
import project.repository.userDao.UserDaoImpl;
import project.service.backlog.BacklogService;
import project.service.backlog.BacklogServiceImpl;
import project.service.encoder.PasswordEncoder;
import project.service.goal.GoalService;
import project.service.goal.GoalServiceImpl;
import project.service.mapper.*;
import project.service.sprint.SprintService;
import project.service.sprint.SprintServiceImpl;
import project.service.story.StoryService;
import project.service.story.StoryServiceImpl;
import project.service.user.UserService;
import project.service.user.UserServiceImpl;
import project.service.validator.UserValidator;
import project.service.validator.Validator;

public final class ApplicationContextInjector {
    private static final WrapperConnector CONNECTOR = new WrapperConnector("database");

    private static final UserDao USER_DAO = new UserDaoImpl(CONNECTOR);

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();

    private static final UserMapper USER_MAPPER = new UserMapper();

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, PASSWORD_ENCODER, USER_MAPPER, USER_VALIDATOR);

    private static final BacklogDao BACKLOG_DAO = new BacklogDaoImpl(CONNECTOR);

    private static final BacklogMapper BACKLOG_MAPPER = new BacklogMapper();

    private static final BacklogService BACKLOG_SERVICE = new BacklogServiceImpl(BACKLOG_DAO, BACKLOG_MAPPER);

    private static final GoalDao GOAL_DAO = new GoalDaoImpl(CONNECTOR);

    private static final GoalMapper GOAL_MAPPER = new GoalMapper();

    private static final GoalService GOAL_SERVICE = new GoalServiceImpl(GOAL_DAO, GOAL_MAPPER);

    private static final StoryDao STORY_DAO = new StoryDaoImpl(CONNECTOR);

    private static final StoryMapper STORY_MAPPER = new StoryMapper();

    private static final StoryService STORY_SERVICE = new StoryServiceImpl(STORY_DAO, STORY_MAPPER);

    private static final SprintDao SPRINT_DAO = new SprintDaoImpl(CONNECTOR);

    private static final SprintMapper SPRINT_MAPPER = new SprintMapper();

    private static final SprintService SPRINT_SERVICE = new SprintServiceImpl(SPRINT_DAO, SPRINT_MAPPER);

    private static ApplicationContextInjector injector;

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (injector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (injector == null) {
                    injector = new ApplicationContextInjector();
                }
            }
        }
        return injector;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public BacklogService getBacklogService() {
        return BACKLOG_SERVICE;
    }

    public GoalService getGoalService() {
        return GOAL_SERVICE;
    }

    public StoryService getStoryService() {
        return STORY_SERVICE;
    }

    public SprintService getSprintService() {
        return SPRINT_SERVICE;
    }

}
