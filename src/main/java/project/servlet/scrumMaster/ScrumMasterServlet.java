package project.servlet.scrumMaster;

import project.command.Command;
import project.command.scrumMaster.*;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class ScrumMasterServlet extends HttpServlet {
    private static final Map<String, Command> nameToCommand = new HashMap<>();

    static {
        nameToCommand.put("addDeveloper", new AddDeveloper());
        nameToCommand.put("addGoal", new AddGoal());
        nameToCommand.put("addStory", new CreateStory());
        nameToCommand.put("createProject", new CreateProject());
        nameToCommand.put("createSprint", new CreateSprint());
    }
}
