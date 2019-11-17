<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/2/19
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">

    <title>Registration form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style type="text/css">
        body {
            background: url("img/time.jpg") fixed;
            background-size: cover;
        }

        *[role="form"] {
            max-width: 530px;
            padding: 15px;
            margin: 0 auto;
            border-radius: 0.3em;
            background-color: #f2f2f2;
        }

        *[role="form"] h2 {
            font-family: 'Open Sans' , sans-serif;
            font-size: 40px;
            font-weight: 600;
            color: #000000;
            margin-top: 5%;
            text-align: center;
            text-transform: uppercase;
            letter-spacing: 4px;
        }

    </style>
<%--    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        window.alert = function(){};
        var defaultCSS = document.getElementById('bootstrap-css');
        function changeCSS(css){
            if(css) $('head > link').filter(':first').replaceWith('<link rel="stylesheet" href="'+ css +'" type="text/css" />');
            else $('head > link').filter(':first').replaceWith(defaultCSS);
        }
        $( document ).ready(function() {
            var iframe_height = parseInt($('html').height());
            window.parent.postMessage( iframe_height, 'https://bootsnipp.com');
        });
    </script>
</head>
<body>
<div class="container" style="padding-top:3%;">
    <form class="form-horizontal" role="form" action="user" method="post">
        <input type="hidden" name="command" value="signUp" />
        <h2><fmt:message key="registration.start"/></h2>
        <div class="form-group">
            <label for="firstName" class="col-sm-3 control-label" ><fmt:message key="registration.name"/></label>
            <div class="col-sm-9">
                <input type="text" id="firstName" name = "name" pattern="([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12})" minlength="2" maxlength="15" placeholder=<fmt:message key="registration.inline.name"/> class="form-control" autofocus required>
            </div>
        </div>
        <div class="form-group">
            <label for="lastName" class="col-sm-3 control-label"><fmt:message key="registration.surname"/></label>
            <div class="col-sm-9">
                <input type="text" id="lastName" name = "surname" pattern="([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))" minlength="2" maxlength="15" placeholder=<fmt:message key="registration.inline.surname"/> class="form-control" autofocus required>
            </div>
        </div>
        <div class="form-group">
            <label for="email" type="email" class="col-sm-3 control-label"><fmt:message key="registration.email"/> </label>
            <div class="col-sm-9">
                <input type="email" id="email" name = "email" placeholder=<fmt:message key="registration.inline.email"/> class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-3 control-label"><fmt:message key="registration.password"/></label>
            <div class="col-sm-9">
                <input type="password" id="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" placeholder=<fmt:message key="registration.inline.password"/> class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="repeatedPassword" class="col-sm-3 control-label"><fmt:message key="registration.repeatedPassword"/></label>
            <div class="col-sm-9">
                <input type="password" id="repeatedPassword" name = "repeatedPassword" placeholder=<fmt:message key="registration.inline.password"/> class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3"><fmt:message key="registration.role"/> </label>
            <div class="col-sm-6">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="custom-control custom-radio custom-control-inline">
                            <label for="scrumRadio"></label><input type="radio" class="custom-control-input" id="scrumRadio" name = "role" value="SCRUM_MASTER">
                            <label class="custom-control-label" for="scrumRadio"><fmt:message key="registration.role.scrumMaster"/></label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <label for="devRadio"></label><input type="radio" class="custom-control-input" id="devRadio" name = "role" value="DEVELOPER">
                            <label class="custom-control-label" for="devRadio"><fmt:message key="registration.role.developer"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- /.form-group -->
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <span class="help-block"><fmt:message key="registration.required"/></span>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-block"><fmt:message key="registration.submit"/></button>
    </form> <!-- /form -->
</div> <!-- ./container -->
</body>
</html>

