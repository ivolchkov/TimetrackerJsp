package project.servlet.scrumMaster;

import project.command.Command;
import project.context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ScrumMasterServlet extends HttpServlet {
    private final Map<String, Command> nameToCommand;
    private final Command defaultCommand;

    public ScrumMasterServlet() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();

        this.nameToCommand = injector.getScrumMasterCommands();
        this.defaultCommand = injector.getDefaultCommand();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;
        String action = req.getParameter("command");

        Command currentCommand = this.nameToCommand.getOrDefault(action, this.defaultCommand);
        page = currentCommand.execute(req);

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
