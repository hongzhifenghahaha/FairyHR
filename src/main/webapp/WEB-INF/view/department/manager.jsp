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
                                    <div class="card-body pre-scrollable">
                                        <h5 class="card-title">

                                            <input type="text" id="changeName"
                                                   style="border-style: none;background-color:transparent;border:0;"
                                                   name="de_name" value="${department.name}" disabled="disabled">
                                            <button type="button" class="btn btn-outline-primary" style="float: right"
                                                    onclick="
                                            document.getElementById('changeName').removeAttribute('disabled')">Edit
                                            </button>
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
                                        <div class="row">

                                            <h4 class="card-title" style="padding-left: 10px">
                                                子部门管理</h4>
                                            <div class="col-xl-10">
                                                <a class="lni-plus" style="float:right;" href="/department/add"
                                                   style="background-color: #e22a6f">新增子部门</a>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th>Department ID</th>
                                                                <th>Department Name</th>
                                                                <th></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach items="${requestScope.deletableDepartments}"
                                                                       var="dd">
                                                                <tr>
                                                                    <td>${dd.id}</td>
                                                                    <td>${dd.name}</td>
                                                                    <td>
                                                                        <button type="button"
                                                                                class="btn btn-light btn-rounded"
                                                                                onclick="delete_department('${dd.id}')">
                                                                            delete
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
                                                        <td style="word-wrap:break-word;word-break:break-all;max-width: 200px;">${user_tmp.name}</td>
                                                        <td style="word-wrap:break-word;word-break:break-all;max-width: 200px;">${user_tmp.id}</td>
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
                                                            <c:if test="${user_tmp.id eq user.id}">
                                                                <button type="button" class="btn btn-light btn-rounded">
                                                                    me
                                                                </button>
                                                            </c:if>
                                                            <c:if test="${!(user_tmp.id eq user.id)}">
                                                                <c:if test="${!(isManager eq true)}">
                                                                    <button type="button"
                                                                            class="btn btn-light btn-rounded"
                                                                            onclick="delete_user('${user_tmp.id}')">
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
                                                            <div class="btn-group dropup m-b-10" style="float:right;">
                                                                <button type="button"
                                                                        class="btn btn-outline-primary dropdown-toggle"
                                                                        data-toggle="dropdown" aria-haspopup="true"
                                                                        aria-expanded="false">
                                                                    <span class="sr-only">Toggle Dropdown</span>
                                                                </button>
                                                                <div class="dropdown-menu" x-placement="top-start"
                                                                     style="position: absolute; transform: translate3d(103px, -198px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                                    <a class="dropdown-item"
                                                                       href="/user/profile/${user_tmp.id}">profile</a>
                                                                    <a class="dropdown-item"
                                                                       href="/attendance/record/${user_tmp.id}">Attendence
                                                                        Record</a>
                                                                    <a class="dropdown-item"
                                                                       href="/leave/record/${user_tmp.id}">Leave
                                                                        Requests</a>
                                                                </div>
                                                            </div>
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
                                            <th>Type</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.leaves}" var="l">
                                            <c:if test="${(l.checker eq null)}">
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
                                                            ${l.type}
                                                    </td>
                                                    <td>
                                                            <%--    &lt;%&ndash;               <button type="button" class="btn btn-light btn-rounded"
                                                                               onclick="reject_request('${l.id}')">
                                                                                   reject
                                                                               </button>


                                                                               <button type="button" class="btn btn-info btn-rounded"
                                                                               onclick="pass_request('${l.id}')">
                                                                                   pass
                                                                               </button>
                       &ndash;%&gt;
                                                            <div class="btn-group dropup m-b-10" style="float:right;">
                                                                <button type="button" class="btn btn-common dropdown-toggle"
                                                                        data-toggle="dropdown" aria-haspopup="true"
                                                                        aria-expanded="false"
                                                                        style="background-color: #0275d8">审核
                                                                </button>
                                                                <div class="dropdown-menu" x-placement="top-start"
                                                                     style="position: absolute; transform: translate3d(103px, -198px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                                    <div class="col-12">
                                                                        <input type="text" placeholder="请输入审核意见"
                                                                               name="check_opinion" required/>
                                                                        <br>
                                                                    </div>
                                                                    <div class="row" style="padding-left: 60px">
                                                                        <button type="button"
                                                                                class="btn btn-light btn-rounded"
                                                                                onclick="reject_request('${l.id}')">
                                                                            reject
                                                                        </button>
                                                                        <button type="button"
                                                                                class="btn btn-info btn-rounded"
                                                                                onclick="pass_request('${l.id}')">
                                                                            pass
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>--%>
                                                        <button type="button"
                                                                class="btn btn-common waves-effect waves-light"
                                                                data-toggle="modal" data-target=".bs-example-modal-lg"
                                                                onclick="set_id('${l.id}')">审核
                                                        </button>

                                                    </td>
                                                </tr>
                                            </c:if>
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
            <div class="col-sm-6 col-md-3 m-t-30">
                <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="myLargeModalLabel">审批意见</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            </div>
                            <div class="modal-body">
                                <input id="opinion" type="text" placeholder="请输入审批意见" style="width: 100%"/>
                                <div class="row" style="padding-left: 40%">
                                    <button type="button"
                                            class="btn btn-light btn-rounded"
                                            onclick="reject_request()">
                                        reject
                                    </button>
                                    <button type="button"
                                            class="btn btn-info btn-rounded"
                                            onclick="pass_request()">
                                        pass
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
                        complete: function () {
                            location.reload();
                        }
                    });
                }

                function assign_user(id) {
                    $.ajax({
                        type: "post",
                        data: {"user_id": id},
                        url: "/department/assignUser",
                        complete: function () {
                            location.reload();
                        }
                    });
                }

                function pass_request() {
                    $.ajax({
                        type: "post",
                        data: {
                            "request_id": document.getElementById("opinion").getAttribute("user_id"),
                            "opinion": document.getElementById("opinion").value
                        },
                        url: "/department/passRequest",
                        complete: function () {
                            location.reload();
                        }
                    });
                }

                function reject_request() {
                    $.ajax({
                        type: "post",
                        data: {
                            "request_id": document.getElementById("opinion").getAttribute("user_id"),
                            "opinion": document.getElementById("opinion").value
                        },
                        url: "/department/rejectRequest",
                        complete: function () {
                            location.reload();
                        }
                    });
                }

                function delete_department(id) {
                    $.ajax({
                        type: "post",
                        data: {"department_id": id},
                        url: "/department/delete",
                        dataType: "json",
                        complete: function(data) {
                            if(data.responseJSON.hasChild) {
                                window.alert("该部门有子部门,删除失败");
                            }
                            else {
                                location.reload();
                            }
                        }
                    });
                }

                function set_id(id) {
                    document.getElementById("opinion").setAttribute("user_id", id);
                }
            </script>

            <c:import url="../footer.jsp"/>
        </div>
    </div>
</div>


</body>
</html>