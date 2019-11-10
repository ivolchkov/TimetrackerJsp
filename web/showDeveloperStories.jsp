<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="${param.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Developer service</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/service.css" rel="stylesheet">
</head>

<body>
<c:import url="service-header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <c:import url="developer-service-sideBar.jsp"/>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="service.developer.sideBar.yourStories"/></h1>
            </div>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th><fmt:message key="service.admin.showStories.name"/></th>
                        <th><fmt:message key="service.admin.showStories.spentTime"/></th>
                        <th><fmt:message key="service.admin.showStories.descr"/></th>
                        <th><fmt:message key="service.admin.showStories.status"/></th>
                        <th><fmt:message key="service.admin.showStories.goalId"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${stories}" var="stories">
                        <tr>
                            <td>${stories.getId()}</td>
                            <td>${stories.getName()}</td>
                            <td>${stories.getSpentTime()}</td>
                            <td>${stories.getDescription()}</td>
                            <td>${stories.getStatus().getDescription()}</td>
                            <td>${stories.getGoal().getId()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:import url="developer-service-paginating.jsp"/>
        </main>
    </div>
</div>

</body>
</html>
