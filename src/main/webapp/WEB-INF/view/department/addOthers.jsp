<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="customTag" %>
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
                                    <div class="col-lg-3"><h4 class="card-title">选择用户加入本部门</h4>
                                    </div>

                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table id="datatable" class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Phone Number</th>
                                                <th>Email</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${others}" var="o">
                                                <tr>
                                                    <td>${o.id}</td>
                                                    <td>${o.name}</td>
                                                    <td>${o.phoneNumber}</td>
                                                    <td>${o.emailAddr}</td>
                                                    <td>
                                                        <button type="submit" class="btn btn-light btn-rounded"
                                                                onclick="add_users('${o.id}')">
                                                            add
                                                        </button>

                                                        <c:set var="isRover" value="false" scope="page" />
                                                        <c:forEach items="${rovers}" var="r">
                                                            <c:if test="${r.id eq o.id}">
                                                                  <c:set var="isRover" value="true" scope="page" />
                                                            </c:if>
                                                        </c:forEach>

                                                        <c:if test="${isRover eq 'true'}">
                                                            <button type="submit" class="btn btn-light btn-rounded"
                                                                    onclick="delete_user('${o.id}')">
                                                                delete
                                                            </button>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-lg-3">
                                        <form action="/department/${department.id}" method="get">
                                            <button class="btn btn-info">Return</button>
                                        </form>
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

            <script>
                function add_users(id) {
                    $.ajax({
                        type: "post",
                        data: {"user_id": id},
                        url: "/department/${department.id}/addOldUser",
                        complete: function() {
                            location.reload();
                        }
                    });
                }

                function delete_user(id) {
                    $.ajax({
                        type: "post",
                        data: {"user_id": id},
                        url: "/department/${department.id}/deleteRover",
                        complete: function () {
                            location.reload();
                        }
                    });
                }
            </script>

            <ct:footer/>
        </div>
    </div>
</div>


</body>
</html>
