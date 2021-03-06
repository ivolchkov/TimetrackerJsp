<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>TimeTracker</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/cover/">
    <link href="css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="css/cover.css" rel="stylesheet">
</head>
<body class="text-center">
<div class="cover-container d-flex w-200 h-200 p-3 mx-auto flex-column">
    <header class="masthead mb-auto">
        <div class="inner">
            <nav class="nav nav-masthead justify-content-center">
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                        data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="nav-link" href="index.jsp">Timetracker</a>
                <a class="nav-link" href="sign-in.jsp"><label><fmt:message key="main.nav.signIn"/></label></a>
                <a class="nav-link" href="register.jsp"><label><fmt:message key="main.nav.signUp"/></label></a>
                <a style="position:absolute; right:150px;" class="nav-link" href="index.jsp?lang=en">English</a>
                <a style="position:absolute; right:70px;" class="nav-link" href="index.jsp?lang=ru">Русский</a>
            </nav>

        </div>
    </header>

    <main role="main" class="inner cover">
        <h1 class="cover-heading"><fmt:message key="main.home.header"/></h1>
        <p class="lead"><fmt:message key="main.home.text"/></p>
        <p class="lead">
            <a href="https://www.atlassian.com/agile/scrum" class="btn btn-lg btn-secondary"><fmt:message
                    key="main.home.learn"/></a>
        </p>
    </main>

    <footer class="mastfoot mt-auto">
        <div class="inner">
            <p> <fmt:message key="footer.copyright"/></p>
        </div>
    </footer>
</div>
</body>
</html>
