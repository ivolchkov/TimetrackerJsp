package project.context;

import org.apache.commons.dbcp.BasicDataSource;
import project.command.Command;
import project.command.admin.*;
import project.command.developer.AddStory;
import project.command.scrumMaster.*;
import project.command.user.SignIn;
import project.command.user.SignOut;
import project.command.user.SignUp;
import project.domain.user.User;
import project.exception.InvalidCommandException;
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

import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {
    private static final String DATABASE_PROPERTY = "database";

    private static final BasicDataSource POOL = new BasicDataSource();

    private static final WrapperConnector CONNECTOR = new WrapperConnector(DATABASE_PROPERTY, POOL);

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

    private static final Command SIGN_IN_COMMAND = new SignIn(USER_SERVICE);

    private static final Command SIGN_UP_COMMAND = new SignUp(USER_SERVICE);

    private static final Command SIGN_OUT_COMMAND = new SignOut();

    private static final Command SHOW_PROJECTS_COMMAND = new ShowProjects(BACKLOG_SERVICE);

    private static final Command SHOW_GOALS_COMMAND = new ShowGoals(GOAL_SERVICE);

    private static final Command SHOW_STORIES_COMMAND = new ShowStories(STORY_SERVICE);

    private static final Command SHOW_SPRINTS_COMMAND = new ShowSprints(SPRINT_SERVICE);

    private static final Command SHOW_USERS_COMMAND = new ShowUsers(USER_SERVICE);

    private static final Command ADD_STORY_COMMAND = new AddStory(storyService);

    private static final Command ADD_DEVELOPER_COMMAND = new AddDeveloper();

    private static final Command ADD_GOAL_COMMAND = new AddGoal();

    private static final Command CREATE_PROJECT_COMMAND = new CreateProject();

    private static final Command CREATE_SPRINT_COMMAND = new CreateSprint();

    private static final Command CREATE_STORY_COMMAND = new CreateStory();

    private static final Command DEFAULT_COMMAND = request -> {
        throw new InvalidCommandException("There is no such command");
    };

    private static final Map<String, Command> USER_COMMANDS = new HashMap<>();

    static {
        USER_COMMANDS.put("signIn", SIGN_IN_COMMAND);
        USER_COMMANDS.put("signUp", SIGN_UP_COMMAND);
        USER_COMMANDS.put("signOut", SIGN_OUT_COMMAND);
    }

    private static final Map<String, Command> ADMIN_COMMANDS = new HashMap<>();

    static {
        ADMIN_COMMANDS.put("showProjects", SHOW_PROJECTS_COMMAND);
        ADMIN_COMMANDS.put("showSprints", SHOW_SPRINTS_COMMAND);
        ADMIN_COMMANDS.put("showGoals", SHOW_GOALS_COMMAND);
        ADMIN_COMMANDS.put("showStories", SHOW_STORIES_COMMAND);
        ADMIN_COMMANDS.put("showUsers", SHOW_USERS_COMMAND);
    }

    private static final Map<String, Command> DEVELOPER_COMMANDS = new HashMap<>();

    static {
        DEVELOPER_COMMANDS.put("addStory", ADD_STORY_COMMAND);
    }

    private static final Map<String, Command> SCRUM_MASTER_COMMANDS = new HashMap<>();

    static {
        SCRUM_MASTER_COMMANDS.put("addDeveloper", ADD_DEVELOPER_COMMAND);
        SCRUM_MASTER_COMMANDS.put("addGoal", ADD_GOAL_COMMAND);
        SCRUM_MASTER_COMMANDS.put("createProject", CREATE_PROJECT_COMMAND);
        SCRUM_MASTER_COMMANDS.put("createSprint", CREATE_SPRINT_COMMAND);
        SCRUM_MASTER_COMMANDS.put("createStory", CREATE_STORY_COMMAND);
    }


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

    public Command getDefaultCommand() {
        return DEFAULT_COMMAND;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMANDS;
    }

    public Map<String, Command> getAdminCommands() {
        return ADMIN_COMMANDS;
    }

    public Map<String, Command> getDeveloperCommands() {
        return DEVELOPER_COMMANDS;
    }

    public Map<String, Command> getScrumMasterCommands() {
        return SCRUM_MASTER_COMMANDS;
    }
}
