<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/10/19
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Scrum master side bar</title>
</head>
<body>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="scrum-master-service.jsp">
                    <span data-feather="home"></span>
                    <fmt:message key="service.admin.sideBar.home"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="createProject.jsp">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.scrumMaster.sideBar.createProject"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="scrum-master?command=showAllBacklogs">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.scrumMaster.sideBar.createGoal"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="scrum-master?command=showAllGoals">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.scrumMaster.sideBar.createStory"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="createSprint.jsp">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.scrumMaster.sideBar.createSprint"/>
                </a>
            </li>
        </ul>
    </div>
</nav>

<script src="js/feather.min.js"></script>
<script>
    feather.replace()
</script>
</body>
</html>
