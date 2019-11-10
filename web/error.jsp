<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/3/19
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="${param.lang}">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Sorry for the inconvenience</title>

    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,900" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="css/style.css" />

</head>

<body>

<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1><fmt:message key="error.oops"/></h1>
        </div>
        <h2><fmt:message key="error.notFound"/></h2>
        <p><fmt:message key="error.message"/></p>
        <a href="index.jsp"><fmt:message key="error.back"/></a>
    </div>
</div>

</body>

</html>

