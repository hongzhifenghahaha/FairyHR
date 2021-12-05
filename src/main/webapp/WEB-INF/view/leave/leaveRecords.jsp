<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="customTag"%>
<%@ taglib prefix="taglib" uri="customTag" %>
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
        <c:import url="../navigation.jsp"/>
        <div class="page-container">

            <div class="main-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-xs-12">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <div class="row">

                                        <h4 class="card-title col-md-1">
                                            <c:if test="${no_add eq '1'}">${user_checked.name}的</c:if>请假申请</h4>
                                        <div class="col-md-10"></div>
                                        <div class="col-md-1">
                                            <c:if test="${no_add eq '0'}">
                                                <a class="lni-plus" style="float:right;" href="/leave/add"
                                                   style="background-color: #e22a6f">新增请假</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-body">

                                    <div class="tab-info">
                                        <ul class="nav nav-tabs" role="tablist">
                                            <li class="nav-item">
                                                <a href="#default-tab-1" class="nav-link active" role="tab"
                                                   data-toggle="tab">待审核</a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="#default-tab-2" class="nav-link" role="tab" data-toggle="tab">已审核</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="default-tab-1">

                                                <div class="main-content"
                                                     style="padding-top: 0px ;padding: 0px; background-color: white">
                                                    <div class="container-fluid">
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <div class="card">
                                                                    <div class="card-body">
                                                                        <div class="table-responsive">
                                                                            <table id="datatable"
                                                                                   class="table table-bordered">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th>Submit Time</th>
                                                                                    <th>User ID</th>
                                                                                    <th>User Name</th>
                                                                                    <th>Start Time</th>
                                                                                    <th>End Time</th>
                                                                                    <th>Reason</th>
                                                                                    <th>Status</th>
                                                                                </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                <c:forEach items="${unchecked_request}"
                                                                                           var="lr">
                                                                                    <taglib:LeaveListItemTag item="${lr}">
                                                                                    </taglib:LeaveListItemTag>
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


                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="default-tab-2">
                                                <div class="main-content"
                                                     style="padding-top: 0px ;padding: 0px; background-color: white">
                                                    <div class="container-fluid">
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <div class="card">
                                                                    <div class="card-body">
                                                                        <div class="table-responsive">
                                                                            <table id="datatable2"
                                                                                   class="table table-bordered">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th>Submit Time</th>
                                                                                    <th>User ID</th>
                                                                                    <th>User Name</th>
                                                                                    <th>Start Time</th>
                                                                                    <th>End Time</th>
                                                                                    <th>Checker</th>
                                                                                    <th>Status</th>
                                                                                    <th>CheckOpinion</th>
                                                                                    <th>CheckTime</th>
                                                                                </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                <c:forEach items="${checked_request}"
                                                                                           var="lr1">
                                                                                    <taglib:checkedLeaveListItem item="${lr1}">
                                                                                    </taglib:checkedLeaveListItem>
                                                                                    <%--<tr>
                                                                                        <td>${lr1.submitTime}</td>
                                                                                        <td>${lr1.user.id}</td>
                                                                                        <td>${lr1.user.name}</td>
                                                                                        <td>${lr1.startTime}</td>
                                                                                        <td>${lr1.endTime}</td>
                                                                                        <td>${lr1.checker.name} (${lr1.checker.id})</td>
                                                                                        <td>${lr1.status}</td>
                                                                                        <td>${lr1.checkOpinion}</td>
                                                                                        <td>${lr1.checkTime}</td>
                                                                                    </tr>--%>
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

                                            </div>
                                        </div>
                                    </div>
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

            <script src="/assets/plugins/datatables/jquery.dataTables.min.js"></script>
            <script src="/assets/plugins/datatables/dataTables.bootstrap4.min.js"></script>

            <script src="/assets/plugins/datatables/dataTables.buttons.min.js"></script>
            <script src="/assets/plugins/datatables/buttons.bootstrap4.min.js"></script>
            <script src="/assets/plugins/datatables/jszip.min.js"></script>
            <script src="/assets/plugins/datatables/pdfmake.min.js"></script>
            <script src="/assets/plugins/datatables/vfs_fonts.js"></script>
            <script src="/assets/plugins/datatables/buttons.html5.min.js"></script>
            <script src="/assets/plugins/datatables/buttons.print.min.js"></script>
            <script src="/assets/plugins/datatables/buttons.colVis.min.js"></script>

            <script src="/assets/plugins/datatables/dataTables.responsive.min.js"></script>
            <script src="/assets/plugins/datatables/responsive.bootstrap4.min.js"></script>

            <script src="/assets/js/datatables.init.js"></script>


            <ct:footer/>
        </div>
    </div>
</div>


</body>
</html>
