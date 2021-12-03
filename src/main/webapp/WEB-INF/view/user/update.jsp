<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午4:03
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

                    <div class="breadcrumb-wrapper row">
                        <div class="col-12 col-lg-3 col-md-6">
                            <h4 class="page-title">Form Elements</h4>
                        </div>
                        <div class="col-12 col-lg-9 col-md-6">
                            <ol class="breadcrumb float-right">
                                <li><a href="index.html">Forms</a></li>
                                <li class="active"> / Form elements</li>
                            </ol>
                        </div>
                    </div>

                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-xs-12">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title">update profile</h4>
                                </div>
                                <div class="card-body">
                                    <p class="card-description">
                                        updating your info in the box
                                    </p>
                                    <form class="forms-sample" action="/user/profile/update/${requestScope.profile_user.id}" method="post">
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">User ID</label>
                                            <input type="text" class="form-control" value="${requestScope.profile_user.id}" name="id">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">User Name</label>
                                            <input type="text" class="form-control" id="exampleInputUsername1"
                                                   value="${requestScope.profile_user.name}" name="name">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Phone Number</label>
                                            <input type="text" class="form-control" value="${requestScope.profile_user.phoneNumber}" name="phone">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Email Address</label>
                                            <input type="email" class="form-control" id="exampleInputEmail1"
                                                   value="${requestScope.profile_user.emailAddr}" name="email">
                                        </div>

                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Resident ID</label>
                                            <input type="text" class="form-control" value="${requestScope.profile_user.residentId}" name="resident">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Address</label>
                                            <input type="text" class="form-control" value="${requestScope.profile_user.address}" name="address">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">Password</label>
                                            <input type="password" class="form-control" id="exampleInputPassword1"
                                                   value="${requestScope.profile_user.password}" name="password">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">position</label>
                                            <input type="text" class="form-control" value="${requestScope.profile_user.position}" readonly>
                                        </div>
                                        <button type="submit" class="btn btn-common mr-3">Submit</button>

                                    </form>
                                    <form action="/user/profile/${requestScope.profile_user.id}" method="get">
                                    <button class="btn btn-light">Return</button>
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
