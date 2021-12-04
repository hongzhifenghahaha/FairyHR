<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="customTag"%>
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
            <div class="md:flex flex-col md:flex-row md:min-h-screen w-full max-w-screen-xl mx-auto">
                <textarea id="model" style="display:none;">
                    <%= request.getAttribute("model") %>
                </textarea>
                <script src="/assets/js/go.js"></script>
                <div id="sample">
                    <script src="/assets/js/department-tree.js"></script>
                    <div id="myDiagramDiv"
                         style="background-color: #d5d5d5; border: solid 1px black; height: 500px"></div>
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
</body>
</html>

