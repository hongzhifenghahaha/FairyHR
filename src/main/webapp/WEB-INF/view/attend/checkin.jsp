<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="customTag" %>
<head>
    <title>FairyHR</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="/assets/fonts/line-icons.css">

    <link rel="stylesheet" type="text/css" href="/assets/css/main.css">

    <link rel="stylesheet" type="text/css" href="/assets/css/responsive.css">
</head>
<div class="app header-default side-nav-dark">
    <div class="layout">
        <c:import url="../navigation.jsp"/>

        <div class="page-container">

            <div class="main-content">
                <div class="container-fluid">
                    <div class="row main-button">

                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title"><%=new Date()%>
                                    </h4>
                                </div>
                                <div class="card-body">
                                    <p class="card-description">click the button to check in</p>
                                    <div class="m-t-20">
                                        <form action="/attendance/checkin" method="post">
                                        <button type="submit" class="btn btn-outline-primary btn-rounded">CHECK IN
                                        </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-xl-6 m-b-10">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title">今日签到</h4>
                                </div>
                                <div class="card-body pre-scrollable" style="max-height: 800px">
                                    <div class="table-responsive">
                                        <table class="table mb-0">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Attendence Time</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${today_checkin}" var="today">
                                                <tr>
                                                    <th scope="row">*</th>
                                                    <td>${today.time}</td>
                                                </tr>
                                            </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            <ct:footer/>

        </div>
    </div>
</div>

<script src="/assets/js/jquery-min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery.app.js"></script>
<script src="/assets/js/main.js"></script>
</html>
