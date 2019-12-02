package com.ua.timetracking.filter;

import com.ua.timetracking.entity.Role;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD},
        urlPatterns = {"/scrum-master-service.jsp",
        "/scrum-master-service-paginating.jsp",
        "/scrum-master-service-sideBar.jsp",
        "/createProject.jsp",
        "/createSprint.jsp",
        "/createGoal.jsp",
        "/createStory.jsp"})
public class ScrumMasterSecurity extends AbstractSecurityFilter {
    public ScrumMasterSecurity() {
        this.role = Role.SCRUM_MASTER;
    }
}
