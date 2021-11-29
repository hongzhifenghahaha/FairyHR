<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- import Athiti font from google fonts -->
    <link href="https://fonts.googleapis.com/css?family=Athiti" rel="stylesheet">
    <!-- import Web App Title font -->
    <link href="https://fonts.googleapis.com/css?family=Quicksand|Yanone+Kaffeesatz" rel="stylesheet">
    <!-- linking style.css to index.html -->
    <link rel="stylesheet" type="text/css" href="static/welcome.css">
    <!-- import jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- linking script.js to index.html -->
    <script>

    </script>
</head>
<body>
<c:import url="navigationBar.jsp"/>

<!-- body layout -->
<div class="overall-container">
    <div class="row">

        <!-- left side -->
        <div class="cell-1" style="padding-left: 30px;">
            <h1><strong> Hello! </strong></h1>
            <c:set var="userName" value="${sessionScope.userName}"/>
            <h2><td>
                Welcome ${userName}
            </td></h2>


                <form action="/homePage">
                    <input type="submit" class="button btn btn-primary"
                           style="font-size: 80px; border-radius: 40px;" id="start" value="Enter">
                    </input>
                </form>

        </div>

    </div>
</div>


</body>
</html>
