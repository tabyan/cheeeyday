<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>欢迎访问节假日信息服务平台^_^</title>

    <script src="<c:url value="/plugins/jquery/jquery.js"/>"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/customer/js/generalIndex.js"/>"></script>

    <!-- Bootstrap -->
    <link href="<c:url value="/dist/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="<c:url value="/dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/plugins/bootstrap-notify-0.2.0/css/bootstrap-notify.css"/>" rel="stylesheet">

    <!-- Custom Styles -->
    <link href="<c:url value="/plugins/bootstrap-notify-0.2.0/css/styles/alert-bangtidy.css"/>" rel="stylesheet">
    <link href="<c:url value="/plugins/bootstrap-notify-0.2.0/css/styles/alert-blackgloss.css"/>" rel="stylesheet">
    <link href="<c:url value="/plugins/bootstrap-datetimepicker/prettify.css"/>" rel="stylesheet">
    <link href="<c:url value="/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"/>" charset="UTF-8"></script>
    <script type="text/javascript" src="<c:url value="/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"/>"></script>
    <script src="<c:url value="/plugins/bootstrap-notify-0.2.0/js/bootstrap-notify.js"/>"></script>
</head>
<body>

<div id="wrap">

    <c:import url="/WEB-INF/views/tags/navbaradmin.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <c:import url="/WEB-INF/views/tags/banner.jsp"/>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="container">
                            <h6>已有节假日<button class="btn btn-success add">新增</button></h6>
                            <table class="table" id="table">
                                <tr>
                                    <td>编号</td>
                                    <td>名称</td>
                                    <td>开始时间</td>
                                    <td>结束时间</td>
                                    <td>操作</td>
                                </tr>
                                <c:forEach var="object" items="${pageResult.result}" varStatus="status">
                                    <tr>
                                        <td>${object.objectid}</td>
                                        <td>${object.name}</td>
                                        <td> <fmt:formatDate value="${object.beginTime}" pattern="yyyy-MM-dd"/></td>
                                        <td> <fmt:formatDate value="${object.endTime}" pattern="yyyy-MM-dd" /></td>
                                        <td>
                                            <button class="btn btn-danger del" data="${status.index}" >删除</button>
                                            <button class="btn btn-success edit" data="${status.index}" >修改</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <hr class="soften">

                        <decorator:body />

                    </div>
                </div><!--/col-->
            </div><!--/row-->
        </div><!--/col-->
    </div><!--/row-->
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="pagination pager">
                <c:choose>
                    <c:when test="${pageResult.totalPage} == 1">
                    </c:when>
                    <c:otherwise>
                        <div class="col-md-12">
                            <c:if test="${pageResult.page > 1}">
                                <li><a href="/general/index/1/8.html" target="_self">首页</a></li>
                                <li><a href="/general/index/${pageResult.page - 1}/8.html" target="_self">上一页</a></li>
                            </c:if>
                            <c:forEach var="page" begin="1" end="${pageResult.totalPage}">
                                <li><a href="/general/index/${page}/8.html" target="_self">${page}</a></li>
                            </c:forEach>
                            <c:if test="${pageResult.totalPage > pageResult.page}">
                                <li><a href="/general/index/${pageResult.page + 1}/8.html" target="_self">下一页</a></li>
                                <li><a href="/general/index/${pageResult.totalPage}/8.html" target="_self">末页</a></li>
                            </c:if>
                        </div>
                    </c:otherwise>
                </c:choose>
                </ul>
            </div>
        </div>
    </div>
    <hr class="soften">
</div>
<div class='notifications bottom-right'></div>

<c:import url="/WEB-INF/views/tags/footer.jsp"/>
<c:import url="/WEB-INF/views/tags/modal.jsp"/>

</body>
</html>
