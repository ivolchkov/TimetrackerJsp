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
            <div class="card">

                <h5 class="card-header info-color white-text text-center py-4">
                    <strong>Create project</strong>
                </h5>

                <!--Card content-->
                <div class="card-body px-lg-5 pt-0">

                    <!-- Form -->
                    <form class="text-center" style="color: #757575;" method="POST" action="scrum-master">
                        <input type="hidden" name="command" value="createProject"/>
                        <!-- Name -->
                        <div class="md-form mt-3">
                            <input type="text" id="projectName" class="form-control" name="projectName">
                            <label for="projectName"><fmt:message key="createProject.projectName"/></label>
                        </div>

                        <!--Message-->
                        <div class="md-form">
                            <textarea id="description" class="form-control md-textarea" rows="5" name ="description"></textarea>
                            <label for="description"><fmt:message key="createProject.description"/></label>
                        </div>

                        <!-- Send button -->
                        <button class="btn btn-outline-info btn-rounded btn-block z-depth-0 my-4 waves-effect" type="submit"><fmt:message key="createProject.submit"/></button>

                    </form>
                    <!-- Form -->

                </div>

            </div>
        </main>
    </div>
</div>

</body>
</html>
