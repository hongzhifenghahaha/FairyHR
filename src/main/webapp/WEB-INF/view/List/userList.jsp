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
            Users List
        </h2>

        <section ng-app="ngTable--Mekaeil" ng-controller="controllTable">

            <section class="addNewUser" ng-hide="hideDefault">
                <form action="/departmentAddUser" method="post">
                    <div class="row">
                        <label for="">ID</label>
                        <input type="text" name="id" placeholder="User ID" >
                    </div>
                    <input type="submit" class="button" value="加入部门"  ng-disabled="incumplete">
                </form>

            </section>

            <section class="userList">
                <table>
                    <thead>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>edit</th>
                    </thead>
                    <c:forEach items="${department.getUsers()}" var="userItem" >
                    <tbody>
                    <tr ng-repeat="user in users">
                            <td>${userItem.getId()}</td>
                            <td>${userItem.getName()}</td>
                            <td>${userItem.getEmailAddr()}</td>
                            <td>
                                <c:if test = "${sessionScope.id != userItem.getId()}">
                                    <form action="/departmentDeleteUser" method="post">
                                    <input name="id" type="text" style="display:none" value="${userItem.getId()}"/>
                                        <input id="allow" type="submit" class="btn2" value="删除"/>
                                    </form>
                                </c:if>
                            </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>
            </section>

        </section>

    </section>
</body>
</html>
