<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="customTag" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>FairyHR</title>

    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="/assets/fonts/line-icons.css">

    <link rel="stylesheet" type="text/css" href="/assets/css/main.css">

    <link rel="stylesheet" type="text/css" href="/assets/css/responsive.css">
</head>
<body>
<div class="app header-default side-nav-dark">
    <div class="layout">
        <c:import url="navigation.jsp"/>
        <div class="page-container">
            <div class="main-content">
                <div class="container-fluid">
                    <div class="row" style="background-color: white">
                        <div class="col-xl-6">
                            <div class="card-body" style="padding-top: 200px;padding-left: 100px;">
                                <h5 style="font-family: 'Mukti Narrow';font-size: 30px;">Welcome to FairyHR</h5>
                                <p class="card-text">Designed to provide you with convenience in attendance and company management</p>
                                <a href="/attendance/checkin" class="btn btn-common">Check In For Today</a>
                            </div>
                        </div>
                        <div class="col-xl-5">
                            <img src="/assets/img/fs-logo.png" width="100%"/>

                        </div>
                    </div>
                </div>

            </div>
            <%--todo:main content--%>
            <ct:footer/>
        </div>
    </div>
</div>

<script src="/assets/js/jquery-min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery.app.js"></script>
<script src="/assets/js/main.js"></script>
</body>
</html>
