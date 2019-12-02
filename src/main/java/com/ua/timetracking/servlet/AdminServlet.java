package com.ua.timetracking.servlet;

import javax.servlet.annotation.WebServlet;


@WebServlet("/admin")
public class AdminServlet extends AbstractServlet {
    public AdminServlet() {
        super("admin");
    }
}
