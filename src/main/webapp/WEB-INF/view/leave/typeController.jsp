<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午1:50
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
                    <div class="row main-button">
                        <div class="col-lg-6 col-xl-6 m-b-10">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title">类型列表</h4>
                                    <a class="lni-plus" style="float:right;" href="/leave/add">新增类型</a>
                                </div>
                                <div class="card-body pre-scrollable" style="max-width: 800px">
                                    <div class="table-responsive">
                                        <table class="table col-6">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>类型</th>
                                                <th> </th>>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${types}" var="t">
                                                <tr>
                                                    <th scope="row">*</th>
                                                    <td>${t}</td>
                                                    <td> <button type="button" class="btn btn-light btn-rounded" onclick="delete_type('${t}')">
                                                        delete
                                                    </button></td>
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
            <c:import url="../footer.jsp" />

        </div>
    </div>
</div>

<script src="/assets/js/jquery-min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery.app.js"></script>
<script src="/assets/js/main.js"></script>
</html>
