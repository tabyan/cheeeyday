<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/dist/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="<c:url value="/dist/js/bootstrap.min.js"/>"></script>
</head>
<body>

<div id="wrap">

    <c:import url="/WEB-INF/views/tags/navbar.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <c:import url="/WEB-INF/views/tags/banner.jsp"/>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="container">

                        </div>

                        <hr class="soften">

                        <decorator:body />

                    </div>
                </div><!--/col-->
            </div><!--/row-->
        </div><!--/col-->
    </div><!--/row-->

    <hr class="soften">
</div>

<c:import url="/WEB-INF/views/tags/footer.jsp"/>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value="/plugins/jquery/jquery.js"/>"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="/dist/js/bootstrap.min.js"/>"></script>
</body>
</html>
