<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<!-- source http://www.scnoob.com More templates http://www.scnoob.com/moban -->
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
<div class="wrapper-page">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-12 col-xs-12">
                <div class="card">
                    <div class="card-header border-bottom text-center">
                        <h4 class="card-title">Sign In</h4>
                    </div>
                    <div class="card-body">
                        <form class="form-horizontal m-t-20" type="post" action="/login">
                            <div class="form-group">
                                <input class="form-control" type="text" required="" placeholder="User ID" name="userid">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="password" required="" placeholder="Password" name="password">
                            </div>
                            <%--<div class="form-group">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                    <label class="custom-control-label" for="customCheck1">Remember me</label>
                                </div>
                            </div>--%>
                            <div class="form-group text-center m-t-20">
                                <button class="btn btn-common btn-block" type="submit">Log In</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="preloader">
    <div class="loader" id="loader-1"></div>
</div>


<script src="/assets/js/jquery-min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery.app.js"></script>
<script src="/assets/js/main.js"></script>
</body>

<!-- source http://www.scnoob.com More templates http://www.scnoob.com/moban -->
</html>