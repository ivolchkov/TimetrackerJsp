<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
                <h1 class="h2"><fmt:message key="service.scrumMaster.sideBar.createProject"/></h1>
            </div>
            <!-- Material form contact -->
            <div class="row">
            <div class="col-sm-6">
                <!--Card content-->
                <div class="card-body px-lg-5 pt-0">
                    <form class="text-center border border-light p-5" method="POST" action="scrum-master">
                        <input type="hidden" name="command" value="createProject"/>

                        <p class="h4 mb-4"><fmt:message key="createProject.info"/></p>

                        <!-- Name -->
                        <label for="defaultContactFormName"></label><input type="text" id="defaultContactFormName"
                                                                           class="form-control mb-4" name="projectName" required placeholder=<fmt:message key="createProject.projectName"/>>

                        <div class="form-group">
                            <label for="exampleFormControlTextarea2"></label><textarea class="form-control rounded-0" name ="description" id="exampleFormControlTextarea2" rows="5" placeholder=<fmt:message key="createProject.description"/>></textarea>
                        </div>

                        <button class="btn btn-info btn-block" type="submit"><fmt:message key="createProject.submit"/></button>
                    </form>
                </div>

            </div>
            </div>
        </main>
    </div>
</div>

</body>
</html>
