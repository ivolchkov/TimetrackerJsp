<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/10/19
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!doctype html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Scrum-master service</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/service.css" rel="stylesheet">
</head>

<body>
<c:import url="service-header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <c:import url="scrum-master-service-sideBar.jsp"/>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="service.scrumMaster.sideBar.createGoal"/></h1>
            </div>
            <!-- Material form contact -->
            <div class="row">
                <div class="col-sm-6">
                <form class="text-center border border-light p-5" method="POST" action="scrum-master">
                    <input type="hidden" name="command" value="addGoal"/>

                    <p class="h4 mb-4"><fmt:message key="createGoal.info"/></p>

                    <!-- Name -->
                    <label for="defaultContactFormName"></label><input type="text" id="defaultContactFormName"
                                                                       class="form-control mb-4" name="name" placeholder=<fmt:message key="createGoal.name"/> required>

                    <p class="h6 mb-6"><fmt:message key="createGoal.backlogId"/></p>
                    <label>
                        <select class="browser-default custom-select mb-4" name="backlogId" required>
                            <option value="" disabled selected><fmt:message key="createGoal.option"/></option>
                            <c:forEach var="elem" items="${backlogs}" varStatus="status">
                                <option value="${status.count}">${elem.projectName}</option>
                            </c:forEach>
                        </select>
                    </label>

                    <button class="btn btn-info btn-block" type="submit"><fmt:message key="createGoal.submit"/></button>
                </form>
                </div>
            </div>
        </main>
    </div>
</div>

</body>
</html>