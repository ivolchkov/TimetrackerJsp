package com.ua.timetracking.filter;

import com.ua.timetracking.entity.Role;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD},
        urlPatterns = {"/developer-service.jsp",
                "/developer-service-paginating.jsp",
                "/developer-service-sideBar.jsp",
                "/freeStories.jsp",
                "/showDeveloperStories.jsp",})
public class DeveloperSecurity extends AbstractSecurityFilter {
    public DeveloperSecurity() {
        this.role = Role.DEVELOPER;
    }
}