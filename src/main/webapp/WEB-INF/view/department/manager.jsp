<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午4:52
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
<%--
          <button type="button" class="btn btn-link">...</button>
          <button type="button" class="btn btn-light btn-rounded">delete</button>
          <button type="button" class="btn btn-light btn-rounded">manager</button>--%>
<div class="app header-default side-nav-dark">
    <div class="layout">
        <c:import url="../navigation.jsp"/>
        <div class="page-container">

            <div class="main-content">

                <div class="container-fluid">


                    <div class="row">
                        <div class="col-lg-5 col-md-12 col-xs-12">
                            <div class="col-lg-12 col-md-12 col-xs-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">

                                            <input type="text" id="changeName" style="border-style: none;background-color:transparent;border:0;" name="de_name" value="${department.name}" disabled="disabled">
                                            <button type="button" class="btn btn-outline-primary" style="float: right" onclick="
                                            document.getElementById('changeName').removeAttribute('disabled')">Edit</button>
                                        </h5>
                                        <div class="row">
                                            <div class="col-6 m-t-20">
                                                <h3 class="text-primary">${department.id}</h3>
                                                <p class="text-muted">部门id</p>
                                            </div>
                                            <div class="col-6 m-t-20">
                                                <h3 class="text-primary">${requestScope.user_num}</h3>
                                                <p class="text-muted">部门人数</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-xs-12 ">
                                <div class="follow">
                                    <div class="card" style="max-height: 640px ">
                                        <div class="card-header">
                                            <h4 class="card-title">用户管理</h4>
                                            <div class="card-toolbar">
                                                <ul>
                                                    <li>
                                                        <a class="lni-plus" style="float:right;"
                                                           href="/department/register"
                                                           style="background-color: #e22a6f">创建新用户</a>
                                                    </li>
                                                    <li>
                                                        <a class="lni-plus" style="float:right;"
                                                           href="/department/addOldUser"
                                                           style="background-color: #e22a6f">添加已有用户</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="table-responsive">
                                            <table id="datatable" class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>ID</th>
                                                    <th>position</th>
                                                    <th></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${requestScope.users}" var="user_tmp">
                                                    <tr>
                                                        <td>${user_tmp.name}</td>
                                                        <td>${user_tmp.id}</td>
                                                        <td>
                                                            <c:if test="${!(user_tmp.position eq null)}">
                                                                ${user_tmp.position}
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:set var="isManager" value="false" scope="page"/>
                                                            <c:forEach items="${requestScope.managers}" var="manager">
                                                                <c:if test="${manager.id eq user_tmp.id}">
                                                                    <c:set var="isManager" value="true" scope="page"/>
                                                                </c:if>
                                                            </c:forEach>
                                                            <a href="/user/profile/${user_tmp.id}">
                                                                <button type="button" class="btn btn-link">more info
                                                                </button>
                                                            </a>
                                                            <c:if test="${user_tmp.id eq user.id}">
                                                                <button type="button" class="btn btn-light btn-rounded">
                                                                    me
                                                                </button>
                                                            </c:if>
                                                            <c:if test="${!(user_tmp.id eq user.id)}">
                                                                <c:if test="${!(isManager eq true)}">
                                                                    <button type="button" class="btn btn-light btn-rounded" onclick="delete_user('${user_tmp.id}')">
                                                                        delete
                                                                    </button>
                                                                </c:if>

                                                            </c:if>

                                                            <c:if test="${isManager eq false}">
                                                                <button type="button" class="btn btn-info btn-rounded"
                                                                        onclick="assign_user('${user_tmp.id}')">
                                                                    assign
                                                                </button>
                                                            </c:if>
                                                            <c:if test="${isManager eq true}">
                                                                <button type="button" class="btn btn-danger btn-rounded"
                                                                >
                                                                    manager
                                                                </button>
                                                            </c:if>

                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-7 col-md-12 col-xs-12">
                            <div class="card " style="max-height: 900px">
                                <div class="card-header">
                                    <h4 class="card-title">请假申请审核区域</h4>
                                </div>
                                <div class="table-responsive ">
                                    <table id="datatable2" class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>User</th>
                                            <th>Start Time</th>
                                            <th>End Time</th>
                                            <th>Reason</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.leaves}" var="l">
                                            <tr>
                                                <td>${l.user.name} (${l.user.id})</td>
                                                <td>
                                                        ${l.startTime}
                                                </td>
                                                <td>
                                                        ${l.endTime}

                                                </td>
                                                <td>
                                                        ${l.reason}
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-light btn-rounded">
                                                        reject
                                                    </button>
                                                    <button type="button" class="btn btn-info btn-rounded">
                                                        pass
                                                    </button>
                                                </td>
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
                function delete_user(id) {
                    $.ajax({
                        type: "post",
                        data: {"user_id": id},
                        url: "/department/deleteUser",
                        complete: function() {
                            location.reload();
                        }
                    });
                }

                function assign_user(id) {
                    $.ajax({
                        type: "post",
                        data: {"user_id": id},
                        url: "/department/assignUser",
                        complete: function() {
                            location.reload();
                        }
                    });
                }
                }
            </script>
            <c:import url="../footer.jsp"/>
        </div>
    </div>
</div>


</body>
</html>