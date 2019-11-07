<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/7/19
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="${param.lang}">
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
                <h1 class="h2"><fmt:message key="service.admin.sideBar.home"/></h1>
            </div>
            <h2><fmt:message key="service.admin.welcome"/></h2>
<%--            <div class="table-responsive">--%>
<%--                <table class="table table-striped table-sm">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th>#</th>--%>
<%--                        <th>Header</th>--%>
<%--                        <th>Header</th>--%>
<%--                        <th>Header</th>--%>
<%--                        <th>Header</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <tr>--%>
<%--                        <td>1,001</td>--%>
<%--                        <td>Lorem</td>--%>
<%--                        <td>ipsum</td>--%>
<%--                        <td>dolor</td>--%>
<%--                        <td>sit</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,002</td>--%>
<%--                        <td>amet</td>--%>
<%--                        <td>consectetur</td>--%>
<%--                        <td>adipiscing</td>--%>
<%--                        <td>elit</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,003</td>--%>
<%--                        <td>Integer</td>--%>
<%--                        <td>nec</td>--%>
<%--                        <td>odio</td>--%>
<%--                        <td>Praesent</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,003</td>--%>
<%--                        <td>libero</td>--%>
<%--                        <td>Sed</td>--%>
<%--                        <td>cursus</td>--%>
<%--                        <td>ante</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,004</td>--%>
<%--                        <td>dapibus</td>--%>
<%--                        <td>diam</td>--%>
<%--                        <td>Sed</td>--%>
<%--                        <td>nisi</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,005</td>--%>
<%--                        <td>Nulla</td>--%>
<%--                        <td>quis</td>--%>
<%--                        <td>sem</td>--%>
<%--                        <td>at</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,006</td>--%>
<%--                        <td>nibh</td>--%>
<%--                        <td>elementum</td>--%>
<%--                        <td>imperdiet</td>--%>
<%--                        <td>Duis</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,007</td>--%>
<%--                        <td>sagittis</td>--%>
<%--                        <td>ipsum</td>--%>
<%--                        <td>Praesent</td>--%>
<%--                        <td>mauris</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,008</td>--%>
<%--                        <td>Fusce</td>--%>
<%--                        <td>nec</td>--%>
<%--                        <td>tellus</td>--%>
<%--                        <td>sed</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,009</td>--%>
<%--                        <td>augue</td>--%>
<%--                        <td>semper</td>--%>
<%--                        <td>porta</td>--%>
<%--                        <td>Mauris</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,010</td>--%>
<%--                        <td>massa</td>--%>
<%--                        <td>Vestibulum</td>--%>
<%--                        <td>lacinia</td>--%>
<%--                        <td>arcu</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,011</td>--%>
<%--                        <td>eget</td>--%>
<%--                        <td>nulla</td>--%>
<%--                        <td>Class</td>--%>
<%--                        <td>aptent</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,012</td>--%>
<%--                        <td>taciti</td>--%>
<%--                        <td>sociosqu</td>--%>
<%--                        <td>ad</td>--%>
<%--                        <td>litora</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,013</td>--%>
<%--                        <td>torquent</td>--%>
<%--                        <td>per</td>--%>
<%--                        <td>conubia</td>--%>
<%--                        <td>nostra</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,014</td>--%>
<%--                        <td>per</td>--%>
<%--                        <td>inceptos</td>--%>
<%--                        <td>himenaeos</td>--%>
<%--                        <td>Curabitur</td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>1,015</td>--%>
<%--                        <td>sodales</td>--%>
<%--                        <td>ligula</td>--%>
<%--                        <td>in</td>--%>
<%--                        <td>libero</td>--%>
<%--                    </tr>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </div>--%>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>

</body>
</html>

