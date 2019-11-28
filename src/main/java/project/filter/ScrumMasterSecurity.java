package project.filter;

import project.entity.Role;

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
public class ScrumMasterSecurity extends AbstractFilter {
    public ScrumMasterSecurity() {
        this.role = Role.SCRUM_MASTER;
    }
}
