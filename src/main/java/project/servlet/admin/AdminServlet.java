package project.servlet.admin;

import project.command.Command;
import project.command.admin.ShowProjects;
import project.command.admin.ShowSprints;
import project.command.admin.ShowUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {
    private static final Map<String, Command> nameToCommand = new HashMap<>();

    static {
        nameToCommand.put("showProjects", new ShowProjects());
        nameToCommand.put("showSprints", new ShowSprints());
        nameToCommand.put("showUsers", new ShowUsers());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello Admin");
    }
}
