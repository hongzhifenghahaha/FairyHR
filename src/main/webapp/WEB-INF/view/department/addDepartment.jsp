<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午4:03
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
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-xs-12">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title">为 ${department.name} 添加子部门</h4>
                                </div>
                                <div class="card-body">
                                    <form class="forms-sample"
                                          action="/department/add" method="post" id="prevent">
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Department ID</label>
                                            <input type="text" class="form-control" name="id" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">Department Name</label>
                                            <input type="text" class="form-control" id="exampleInputUsername1"
                                                   name="name" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputUsername1">User selected to Sub Department</label>
                                            <p class="card-description">
                                                (cur department's managers will also be the new department's managers)
                                            </p>
                                            <div class="table-responsive">
                                                <table id="datatable"
                                                       class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>if selected</th>
                                                        <th>User ID</th>
                                                        <th>User Name</th>
                                                        <th>User Position</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${requestScope.candidates}" var="candidate">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="new_managers"
                                                                       value="${candidate.id}"/>
                                                            </td>
                                                            <td style="word-wrap:break-word;word-break:break-all;max-width: 200px;">${candidate.id}</td>
                                                            <td style="word-wrap:break-word;word-break:break-all;max-width: 200px;">${candidate.name}</td>
                                                            <td>
                                                                <c:if test="${!(candidate.position eq null)}">
                                                                    ${candidate.position}
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                        </div>

                                        <button type="submit" class="btn btn-common mr-3">Submit</button>

                                    </form>
                                    <form action="/department/${department.id}" method="get">
                                        <button class="btn btn-light">Return</button>
                                    </form>
                                    <c:if test="${!(msg eq null)}">
                                        <label style="color: #e22a6f">${msg}</label>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <c:import url="../footer.jsp"/>

        </div>
    </div>
</div>

<script src="/assets/js/jquery-min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery.app.js"></script>
<script src="/assets/js/main.js"></script>
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

<script type="text/javascript">
    window.οnlοad = function () {
        var myform = document.getElementById("prevent");
        myform.οnkeypress = function (ev) {
            var ev = window.event || ev;
            if (ev.keyCode == 13 || ev.which == 13) {
                return false;
            }
        }
    }
</script>
</html>
