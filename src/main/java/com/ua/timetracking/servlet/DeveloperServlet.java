package com.ua.timetracking.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/developer")
public class DeveloperServlet extends AbstractServlet {
    public DeveloperServlet() {
        super("developer");
    }
}
