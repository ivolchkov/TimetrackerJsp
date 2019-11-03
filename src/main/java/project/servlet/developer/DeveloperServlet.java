package project.servlet.developer;

import project.command.Command;
import project.command.developer.AddStory;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class DeveloperServlet extends HttpServlet {
    private static final Map<String, Command> nameToCommand = new HashMap<>();

    static {
        nameToCommand.put("addStory", new AddStory());
    }
}
