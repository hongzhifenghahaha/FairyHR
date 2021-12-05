<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="customTag"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>FairyHR</title>

    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="/assets/fonts/line-icons.css">

    <link rel="stylesheet" type="text/css" href="/assets/css/main.css">

    <link rel="stylesheet" type="text/css" href="/assets/css/responsive.css">

    <script>
        function readtext(datas) {
            var date1=$("#startDate").val();
            var y1=date1.split("-")[0];
            var m1=date1.split("-")[1];
            var d1=date1.split("-")[2];
            var time1=new Date(y1,m1,d1);

            var date2=$("#endDate").val();
            var y2=date1.split("-")[0];
            var m2=date1.split("-")[1];
            var d2=date1.split("-")[2];
            var time2=new Date(y2,m2,d2);


            var filteredDates = new Array()
            var count=0;
            for(let item in datas) {
                var date = new Date(item[2]);
                if (Date.parse(item[2]) > Date.parse(date1) && Date.parse(item[2]) < Date.parse(date2)){
                    filteredDates[count]=item;
                    count++;
                }
            };
            sessionStorage.setItem('filteredDates',JSON.stringify(filteredDates))
        }

        function load1(datas) {

            var filteredDates = new Array()
            var count=0;
            for(let item in datas) {
                var date = new Date(item[2]);
                    filteredDates[count]=item;
                    count++;
            };
            sessionStorage.setItem('filteredDates',JSON.stringify(filteredDates))
        }
    </script>
</head>
<body onload="load1('${attendances}')">
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
                                        <%--<form action="/attendanceRange" method="get">--%>
                                        <div class="form-group col-lg-6">
                                            <label for="startDate">开始日期</label>
                                            <input type="date" class="form-control" id="startDate"
                                                   name="start_date">
                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label for="endDate">截至日期</label>
                                            <input type="date" class="form-control" id="endDate"
                                                   name="start_date">
                                        </div>
                                        <input type="button"  class="btn btn-outline-primary" onclick="readtext('${attendances}')">Primary</input>
                                        <%--</form>--%>

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
                                                <c:forEach items="${filteredDates}" var="at">
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


                <ct:footer/>
        </div>
    </div>
</div>


</body>
</html>
