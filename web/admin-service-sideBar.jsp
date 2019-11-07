<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/7/19
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin side bar</title>
</head>
<body>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="admin-service.jsp">
                    <span data-feather="home"></span>
                    <fmt:message key="service.admin.sideBar.home"/> <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.admin.sideBar.projects"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.admin.sideBar.sprints"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin?command=showUsers&currentPage=1&recordsPerPage=10">
                    <span data-feather="users"></span>
                    <fmt:message key="service.admin.sideBar.users"/>
                </a>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
