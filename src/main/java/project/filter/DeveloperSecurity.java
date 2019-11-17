package project.filter;

import project.domain.User;
import project.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD},
        urlPatterns = {"/developer-service.jsp",
        "/developer-service-paginating.jsp",
        "/developer-service-sideBar.jsp",
        "/freeStories.jsp",
        "/showDeveloperStories.jsp",})

public class DeveloperSecurity implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != Role.DEVELOPER) {
            request.getRequestDispatcher("error.jsp").forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest ,servletResponse);
    }

    @Override
    public void destroy() {
    }
}