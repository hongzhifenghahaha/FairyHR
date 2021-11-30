<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>FairyHR</title>
    <link rel="stylesheet" type="text/css" href="/static/askForLeave.css">
</head>
<body>
    <c:import url="../navigationBar.jsp"/>

    <div id="formular">
        <form action="/askForLeave" method="post">
            <fieldset>
                <legend>请假条</legend>
                <p>
                    <label for="input-date">起始日期</label>
                    <input name="startDate" id="input-date" type="date" value="2014-10-31">
                </p>
                <p>
                    <label for="input-time">起始时间</label>
                    <input name="startTime" id="input-time" type="time" value="00:00:01">
                </p>
                    <p>
                        <label for="input-date">结束日期</label>
                        <input name="endDate" id="input-end-date" type="date" value="2014-10-31">
                    </p>
                    <p>
                        <label for="input-time">结束时间</label>
                        <input name="endTime" id="input-end-time" type="time" value="00:00:01">
                    </p>
                    <p>
                        <label for="select-colors">Select</label>
                        <select id="select-colors">
                            <option value="" disabled selected>请假类型</option>
                            <option value="0">公事</option>
                            <option value="1">私事</option>
                            <option value="2">病假</option>
                            <option value="3">其他</option>
                        </select>
                    </p>
                <p>
                    <label for="input-datetime">原因</label>
                    <input name="reason" id="input-datetime" type="text" value="Reasons">
                </p>
                    <p>
                        <input type="submit" class="button" value="提交请假"/>
                    </p>
            </fieldset>
        </form>
        </div>
</body>
</html>
