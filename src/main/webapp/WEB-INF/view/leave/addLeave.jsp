<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-1 col-xl-3"></div>
                        <div class="col-lg-11 col-xl-6">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title">请假申请表</h4>
                                </div>
                                <div class="card-body">
                                    <form class="form" id="leave" action="/leave/add" method="post">
                                        <div class="form-row">
                                            <div class="form-group col-lg-12">
                                                <label for="firstname" class="col-xl-2">所属部门编号: </label>
                                                <c:forEach items="${departs}" var="de">
<%--                                                    <div class="col-xl-3">--%>
                                                        ${de}<input type="radio"  name="depart"
                                                               value="${de}" checked="checked">
                                                </c:forEach>
                                            </div>
                                            <div class="form-group col-lg-6">
                                                <input type="date" class="form-control" id="firstname"
                                                       name="start_date">
                                                <label for="firstname">开始日期</label>
                                            </div>
                                            <div class="form-group col-lg-6">
                                                <input type="time" class="form-control" name="start_time">
                                                <label for="lastname">开始时间</label>
                                            </div>
                                            <div class="form-group col-lg-6">
                                                <input type="date" class="form-control" id="lastname" name="end_date">
                                                <label for="lastname">结束日期</label>
                                            </div>
                                            <div class="form-group col-lg-6">
                                                <input type="time" class="form-control" name="end_time">
                                                <label for="lastname">结束时间</label>
                                            </div>
                                            <div class="form-group col-lg-12">
                                                <input type="text" class="form-control" id="username" name="reason">
                                                <label for="username">请假原因</label>
                                            </div>

                                            <div class="form-group col-lg-12 text-righ">
                                                <button type="submit" class="btn btn-common">Submit</button>
                                            </div>

                                            <c:if test="${!(msg eq null)}">
                                                <label for="username" style="color: #e22a6f">${msg}</label>
                                            </c:if>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <c:import url="../footer.jsp"/>

        </div>
    </div>
</div>

<script src="/assets/js/jquery-min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery.app.js"></script>
<script src="/assets/js/main.js"></script>
</html>
