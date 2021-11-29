<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2021/11/29
  Time: 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>FairyHR</title>
    <link rel="stylesheet" type="text/css" href="/static/navigationBar.css">
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-1.9.0.min.js"></script>
</head>
<body>

<c:set var="userName" value="${sessionScope.userName}"/>
<header class="header">
    <h1 class="logo"><a href="/homePage?">FairyHR</a></h1>
    <ul class="main-nav">
        <li><a href="/homePage?">Home</a></li>
        <li><a href="/about?">About</a></li>
        <c:if test = "${empty sessionScope.id }">
            <li><a href="/?">Sign In/Sign Up</a></li>
        </c:if>
        <c:if test = "${not empty sessionScope.id }">
            <li><a href="/?">${userName}</a></li>
        </c:if>
    </ul>
</header>
</body>
</html>
