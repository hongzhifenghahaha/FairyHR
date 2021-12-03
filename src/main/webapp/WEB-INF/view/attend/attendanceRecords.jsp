<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header border-bottom">
                                        <h4 class="card-title">签到记录</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="datatable" class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Date</th>
                                                    <th>AttendenceTime</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${attendances}" var="at">
                                                    <tr>
                                                        <td>${at[0]}</td>
                                                        <td>${at[1]}</td>
                                                        <td>${at[2]}</td>
                                                        <td>${at[3]}</td>
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


            <c:import url="../footer.jsp"/>
        </div>
    </div>
</div>


</body>
</html>