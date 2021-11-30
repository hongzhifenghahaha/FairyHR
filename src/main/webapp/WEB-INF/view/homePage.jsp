<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>FairyHR</title>
    <link rel="stylesheet" type="text/css" href="/static/homePage.css">
    <script>
        $(function(){
        var $ppc = $('.progress-pie-chart'),
            percent = parseInt($ppc.data('percent')),
            deg = 360*percent/100;
        if (percent > 50) {
            $ppc.addClass('gt-50');
        }
        $('.ppc-progress-fill').css('transform','rotate('+ deg +'deg)');
        $('.ppc-percents span').html(percent+'%');
        });
    </script>
</head>
<body>
    <c:import url="navigationBar.jsp"/>
    <h1>我的部门</h1>
    <%--<c:if test = "${empty sessionScope.department}">--%>
    <div class="progressDiv">
    <div class="statChartHolder">
        <div class="progress-pie-chart" data-percent="67"><!--Pie Chart -->
            <div class="ppc-progress">
                <div class="ppc-progress-fill"></div>
            </div>
            <div class="ppc-percents">
                <div class="pcc-percents-wrapper">
                    <c:if test = "${not empty sessionScope.manager}">
                        <span>Manager</span>
                    </c:if>
                    <c:if test = "${empty sessionScope.manager}">
                        <span>User</span>
                    </c:if>
                </div>
            </div>
        </div><!--End Chart -->
    </div>
    <div class="statRightHolder">
        <ul>
            <li> <h3 class="blue"><c:out value="${department.getName()}"/></h3> <span>Department</span></li>
            <li> <h3 class="purple"><c:out value="${position}"/></h3> <span>Position</span></li>
        </ul>
        <c:if test = "${empty sessionScope.manager}">
            <ul class="statsLeft">
                <li><a href="<c:url value="/checkIn?"/>" class="yellow">今日签到</a> <span>Check In</span></li>
                <li><a href="<c:url value="/attendanceRecord?"/>" class="red">签到记录</a> <span>Attendance Record</span></li>
            </ul>
            <ul class="statsRight">
                <li><a href="<c:url value="/askForLeave?"/>" >发起请假</a> <span>Ask for Leave</span></li>
                <li><a href="<c:url value="/leaveRecord?"/>">请假记录</a> <span>Leave Record</span></li>
            </ul>
        </c:if>
        <c:if test = "${not empty sessionScope.manager}">
            <ul class="statsLeft">
                <li><a href="<c:url value="/checkIn?"/>" class="yellow">今日签到</a> <span>Check In</span></li>
                <li><a href="<c:url value="/attendanceRecord?"/>" class="red">签到记录</a> <span>Attendance Record</span></li>
            </ul>
            <ul class="statsRight">
                <li><a href="<c:url value="/askForLeave?"/>" >发起请假</a> <span>Ask for Leave</span></li>
                <li><a href="<c:url value="/leaveRecord?"/>">请假记录</a> <span>Leave Record</span></li>
            </ul>
        </c:if>
    </div>

</div>
</body>
</html>
