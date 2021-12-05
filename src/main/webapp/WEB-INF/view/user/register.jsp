<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午4:03
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
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-xs-12">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title">为本部门创建一个新用户</h4>
                                </div>
                                <div class="card-body">
                                    <p class="card-description">
                                        updating your info in the box
                                    </p>
                                    <form class="forms-sample"
                                          action="/department/${department.id}/register" method="post">
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">User ID</label>
                                            <input type="text" class="form-control"  name="id"
                                                   onkeyup="value=value.replace(/[^a-zA-Z\d-]/g,'')" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">User Name</label>
                                            <input type="text" class="form-control" id="exampleInputUsername1"
                                                   name="name" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Phone Number</label>
                                            <input type="text" class="form-control" name="phone" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Email Address</label>
                                            <input type="email" class="form-control" id="exampleInputEmail1"
                                                   name="email" required="required">
                                        </div>

                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Resident ID</label>
                                            <input type="text" class="form-control" name="resident" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Address</label>
                                            <input type="text" class="form-control" name="address" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">Password</label>
                                            <input type="password" class="form-control" id="exampleInputPassword1"
                                                   name="password" required="required">
                                        </div>
                                        <button type="submit" class="btn btn-common mr-3">Submit</button>

                                    </form>
                                    <form action="/department/${department.id}" method="get">
                                        <button class="btn btn-light">Return</button>
                                    </form>
                                    <c:if test="${!(msg eq null)}">
                                        <label  style="color: #e22a6f">${msg}</label>
                                    </c:if>
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
