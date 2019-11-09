<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/9/19
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Developer side bar</title>
</head>
<body>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="admin-service.jsp">
                    <span data-feather="home"></span>
                    <fmt:message key="service.admin.sideBar.home"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="developer?command=showFreeStories&currentPage=1&recordsPerPage=10">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.developer.sideBar.freeStories"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="developer?command=showDeveloperStories&currentPage=1&recordsPerPage=10">
                    <span data-feather="file-text"></span>
                    <fmt:message key="service.developer.sideBar.yourStories"/>
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
