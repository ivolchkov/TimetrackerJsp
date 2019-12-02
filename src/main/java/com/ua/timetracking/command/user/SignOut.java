package com.ua.timetracking.command.user;

import com.ua.timetracking.command.Command;

import javax.servlet.http.HttpServletRequest;

public class SignOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();

        return "index.jsp";
    }
}
