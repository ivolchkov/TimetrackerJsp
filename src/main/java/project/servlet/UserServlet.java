package project.servlet;

import javax.servlet.annotation.WebServlet;


@WebServlet("/user")
public class UserServlet extends AbstractServlet {
    public UserServlet() {
        super("user");
    }
}
