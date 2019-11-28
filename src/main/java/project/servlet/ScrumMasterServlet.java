package project.servlet;

import javax.servlet.annotation.WebServlet;


@WebServlet("/scrum-master")
public class ScrumMasterServlet extends AbstractServlet {
    public ScrumMasterServlet() {
        super("scrum-master");
    }
}
