<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/8/19
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Admin service</title>

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
        <c:import url="admin-service-sideBar.jsp"/>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="service.admin.sideBar.goals"/></h1>
            </div>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th><fmt:message key="service.admin.showGoals.name"/></th>
                        <th><fmt:message key="service.admin.showGoals.backlogId"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${goals}" var="goals">
                        <tr>
                            <td>${goals.getId()}</td>
                            <td>${goals.getName()}</td>
                            <td>${goals.getBacklog().getId()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:import url="admin-service-paginating.jsp"/>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>


</body>
</html>
