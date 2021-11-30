<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>FairyHR</title>
    <link rel="stylesheet" type="text/css" href="/static/userList.css">

</head>
<body>
<c:import url="../navigationBar.jsp"/>

<section class="wrapper">
    <h2>
        Leave Request List
    </h2>

    <section ng-app="ngTable--Mekaeil" ng-controller="controllTable">

        <section class="userList">
            <table>
                <thead>
                <th>请假条ID</th>
                <th>请假人</th>
                <th>起始日期</th>
                <th>结束日期</th>
                <th>原因</th>
                <th>审核</th>
                </thead>
                <c:forEach items="${department.getLeaveRequests()}" var="leaveRequestItem" >
                    <c:if test = "${ leaveRequestItem.getStatus() == '待审核'}">
                    <tbody>
                    <tr ng-repeat="user in users">
                        <td>${leaveRequestItem.getId()}</td>
                        <td>${leaveRequestItem.getUser().getName()}</td>
                        <td>${leaveRequestItem.getStartTime().toString()}</td>
                        <td>${leaveRequestItem.getEndTime().toString()}</td>
                        <td>${leaveRequestItem.getReason()}</td>
                        <td>
                                <form action="/allowLeaveRequestItem" method="post">
                                    <input name="id" type="text" style="display:none" value="${leaveRequestItem.getId()}"/>
                                    <input id="allow" type="submit" class="btn1" value="批准"/>
                                </form>
                                <form action="/refuseLeaveRequestItem" method="post">
                                    <input name="id" type="text" style="display:none" value="${leaveRequestItem.getId()}"/>
                                    <input id="refuse" type="submit" class="btn2" value="驳回"/>
                                </form>
                        </td>
                    </tr>
                    </tbody>
                    </c:if>
                </c:forEach>
            </table>
        </section>

    </section>

</section>
</body>
</html>
