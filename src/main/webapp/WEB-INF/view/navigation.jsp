<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2021/12/4
  Time: 上午12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="header navbar">
    <div class="header-container">
        <div class="nav-logo" style="width: 300px;">
            <a href="/home">
                <span class="logo">
                      <img src="/assets/img/title.png" alt="" height="60px">
                </span>

            </a>
            <a href="/home">
                <span class="logo">
                      <img src="/assets/img/title2.png" alt="" height="60px">
                </span>

            </a>
        </div>
        <ul class="nav-left" style="padding-top: 20px;">
            <li>
                <a class="sidenav-fold-toggler" href="javascript:void(0);">
                    <i class="lni-menu"></i>
                </a>
                <a class="sidenav-expand-toggler" href="javascript:void(0);">
                    <i class="lni-menu"></i>
                </a>
            </li>
        </ul>
        <ul class="nav-right">
            <li class="user-profile dropdown dropdown-animated scale-left">

                <c:choose>
                    <c:when test="${user.name eq null}">
                        <a href="/login" class="dropdown-toggle">
                            sign in
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: #e22a6f">
                                ${user.name}
                        </a>
                    </c:otherwise>
                </c:choose>


                <c:choose>
                    <c:when test="${user.name eq null}">
                    </c:when>
                    <c:otherwise>
                        <ul class="dropdown-menu dropdown-md">
                            <li>
                                <ul class="list-media">
                                    <li class="list-item avatar-info">
                                        <div class="info">
                                            <span class="title text-semibold">${user.name}</span>
                                            <span class="sub-title">id: ${user.id}</span>
                                            <span class="sub-title">${user.position}</span>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <li role="separator" class="divider"></li>
                            <li>
                                <a href="/user/profile/${id}">
                                    <i class="lni-user"></i>
                                    <span>Profile</span>
                                </a>
                            </li>
                            <li>
                                <a href="/logout">
                                    <i class="lni-lock"></i>
                                    <span>Logout</span>
                                </a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>

            </li>
        </ul>
        <ul class="nav-left" style="float: right">
            <p class="sub-nav" style="padding-top: 18px" >
                Welcome
            </p>
        </ul>
    </div>
</div>


<div class="side-nav expand-lg">
    <div class="side-nav-inner">
        <ul class="side-nav-menu">
            <li class="side-nav-header">
                <span>导航</span>
            </li>
            <li class="nav-item dropdown">
                <a href="#" class="dropdown-toggle">
                    <span class="icon-holder">
                        <i class="lni-dashboard"></i>
                    </span>
                    <span class="title">考勤管理</span>
                    <span class="arrow">
                        <i class="lni-chevron-right"></i>
                    </span>
                </a>
                <ul class="dropdown-menu sub-down">
                    <li>
                        <a href="/attendance/checkin">签到考勤</a>
                    </li>
                    <li>
                        <a href="/attendance/record">历史记录</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a class="dropdown-toggle" href="/leave/record">
                    <span class="icon-holder">
                    <i class="lni-cloud"></i>
                    </span>
                    <span class="title">请假管理</span>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="dropdown-toggle" href="/department">
                    <span class="icon-holder">
                    <i class="lni-layers"></i>
                    </span>
                    <span class="title">部门管理</span>
                </a>
            </li>
<%--            ${position}
            <c:if test="${position eq 'manager'}">
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle" href="/department">
                    <span class="icon-holder">
                    <i class="lni-layers"></i>
                    </span>
                        <span class="title">部门管理</span>
                    </a>
                </li>
            </c:if>--%>
        </ul>
    </div>
</div>

</html>
