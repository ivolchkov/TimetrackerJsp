package project.filter;

import project.entity.Role;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD},
        urlPatterns = {"/admin-service.jsp",
                "/admin-service-paginating.jsp",
                "/admin-service-sideBar.jsp",
                "/showProjects.jsp",
                "/showSprints.jsp",
                "/showGoals.jsp",
                "/showStories.jsp",
                "/showUsers.jsp"})
public class AdminSecurity extends AbstractFilter {
    public AdminSecurity() {
        this.role = Role.ADMIN;
    }
}
