<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/10/19
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
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
                <h1 class="h2"><fmt:message key="service.scrumMaster.sideBar.createStory"/></h1>
            </div>
            <!-- Material form contact -->
            <div class="row">
                <div class="col-sm-6">
                    <form class="text-center border border-light p-5" method="POST" action="scrum-master">
                        <input type="hidden" name="command" value="createStory"/>

                        <p class="h4 mb-4"><fmt:message key="createStory.info"/></p>

                        <!-- Name -->
                        <label for="defaultContactFormName"></label><input type="text" id="defaultContactFormName"
                                                                           class="form-control mb-4" name="name" placeholder=<fmt:message key="createStory.name"/> required>

                        <p class="h6 mb-6"><fmt:message key="createStory.spentTime"/></p>
                        <label>
                            <input type="time" class="form-control mb-4" name="spentTime" required>
                        </label>

                        <div class="form-group">
                            <label for="exampleFormControlTextarea2"></label><textarea class="form-control rounded-0" name ="description" id="exampleFormControlTextarea2" rows="5" placeholder=<fmt:message key="createStory.description"/>></textarea>
                        </div>

                        <p class="h6 mb-6"><fmt:message key="createStory.goalId"/></p>
                        <label>
                            <select class="browser-default custom-select mb-4" name="goalId" required>
                                <option value="" disabled selected><fmt:message key="createStory.option"/></option>
                                <c:forEach var="elem" items="${goals}" varStatus="status">
                                    <option value="${status.count}">${elem.name}</option>
                                </c:forEach>
                            </select>
                        </label>

                        <button class="btn btn-info btn-block" type="submit"><fmt:message key="createStory.submit"/></button>
                    </form>
                </div>
            </div>
        </main>
    </div>
</div>

</body>
</html>
