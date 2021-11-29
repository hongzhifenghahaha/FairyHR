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
    <h1 class="logo"><a href="<c:url value="/homePage?"/>">FairyHR</a></h1>
    <ul class="main-nav">
        <li><a href="<c:url value="/homePage?"/>">Home</a></li>
        <li><a href="<c:url value="/about?"/>">About</a></li>
        <c:if test = "${empty sessionScope.id }">
            <li><a href="<c:url value="/?"/>">Sign In/Sign Up</a></li>
        </c:if>
        <c:if test = "${not empty sessionScope.id }">
            <li><a href="<c:url value="/?"/>">${userName}</a></li>
        </c:if>
    </ul>
</header>
</body>
</html>
