<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午3:37
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

                    <div class="breadcrumb-wrapper row">
                        <div class="col-12 col-lg-3 col-md-6">
                            <h4 class="page-title">Profile</h4>
                        </div>
                    </div>

                </div>
                <div class="container-fluid">
                    <div class="row">

                        <div class="col-12 col-md-12">
                            <div class="profile-bg">
                                <div class="user-profile">

                                    <div class="profile-body">
                                        <h3 class="profile-user-name">${requestScope.profile_user.name}</h3>
                                        <small class="profile-user-address">${requestScope.profile_user.position}</small>
                                        <div class="profile-user-description">
                                            <p>${requestScope.profile_user.address}</p>
                                        </div>
                                        <div class="m-t-5">
                                            <a href="/user/profile/update/${requestScope.profile_user.id}" class="btn btn-common">Edit Profile</a>
                                        </div>
                                    </div>
                                    <div class="row border-top m-t-20">
                                        <div class="col-4 border-right d-flex flex-column justify-content-center align-items-center py-4">
                                            <h3>id</h3>
                                            <small>${requestScope.profile_user.id}</small>
                                        </div>
                                        <div class="col-4 border-right d-flex flex-column justify-content-center align-items-center py-4">
                                            <h3>电话号码</h3>
                                            <small>${requestScope.profile_user.phoneNumber}</small>
                                        </div>
                                        <div class="col-4 border-right d-flex flex-column justify-content-center align-items-center py-4">
                                            <h3>Email</h3>
                                            <small>${requestScope.profile_user.emailAddr}</small>
                                        </div>
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
