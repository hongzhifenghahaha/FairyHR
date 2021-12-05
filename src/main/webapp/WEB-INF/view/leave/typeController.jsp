<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="customTag" %>
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
                                    <h4 class="card-title">请假类型</h4>
                                    <button type="button"
                                            class="btn btn-outline-info waves-effect waves-light" style="float: right"
                                            data-toggle="modal" data-target=".bs-example-modal-lg">添加请假类型
                                    </button>
                                </div>
                                <div class="card-body pre-scrollable" style="max-height: 800px">

                                    <div class="table-responsive">
                                        <table class="table mb-0">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Type Name</th>
                                                <th></th>

                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${types}" var="t">
                                                <tr>
                                                    <td>
                                                            *
                                                    </td>
                                                    <td>
                                                            ${t}
                                                    </td>

                                                    <td>
                                                        <button id="type_naming" type="button" class="btn btn-light btn-rounded"
                                                                onclick="delete_type()" type_name="${t}">
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
            <ct:footer/>
            <div class="col-sm-6 col-md-3 m-t-30">
                <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="myLargeModalLabel">创建新类型</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            </div>
                            <div class="modal-body">
                                <form>
                                <input id="new_name" type="text" placeholder="请输入审批意见" style="width: 100%" required/>
                                <div class="row" style="padding-left: 40%">
                                    <button type="button"
                                            class="btn btn-light btn-rounded"
                                            onclick="add_type()">
                                        提交
                                    </button>
                                </div>
                                </form>
                            </div>
                        </div>
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

<script>
    function delete_type(){
        $.ajax({
            type: "post",
            data: {"type_name": document.getElementById("type_naming").getAttribute("type_name")},
            url: "/leave/type/delete",
            dataType: "json",
            complete: function (data) {
                location.reload();
            }
        });
    }

    function add_type(){
        if (document.getElementById("new_name").value == ""){
            window.alert("输入的新类型不能为空");
        }else{
            $.ajax({
                type: "post",
                data: {"type_name": document.getElementById("new_name").value},
                url: "/leave/type/add",
                dataType: "json",
                complete: function (data) {
                    location.reload();
                }
            });
        }
    }
</script>
</html>
