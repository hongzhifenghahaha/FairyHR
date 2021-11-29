<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>FairyHR</title>
  <link rel="stylesheet" type="text/css" href="/static/loginStyle.css">
</head>
<body>

<c:import url="navigationBar.jsp"/>

<div class="login-wrap">
  <div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
    <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
    <div class="login-form">
      <form action="/login" class="sign-in-htm" method="post">
        <div class="group">
          <label>Id</label>
          <input name="id" type="text" class="input">
        </div>
        <div class="group">
          <label>Password</label>
          <input name="password" type="password" class="input" data-type="password">
        </div>
        <div class="group">
          <input id="check" name="manager" type="checkbox" class="check" checked>
          <label for="check"><span class="icon"></span> 我是管理员</label>
        </div>
        <div class="group">
          <input type="submit" class="button" value="Sign In">
        </div>
        <div class="hr"></div>
        <div class="foot-lnk">
          <a href="#forgot">Forgot Password?</a>
        </div>
      </form>
      <form action="/register" class="sign-up-htm" method="post">
        <div class="group">
          <label>Id</label>
          <input name="id" type="text" class="input">
        </div>
        <div class="group">
          <label>UserName</label>
          <input name="userName" type="text" class="input">
        </div>
        <div class="group">
          <label>Password</label>
          <input name="password" type="password" class="input" data-type="password">
        </div>
        <div class="group">
          <label>Email</label>
          <input name="email" type="text" class="input">
        </div>
        <div class="group">
          <input type="submit" class="button" value="Sign Up">
        </div>
        <div class="hr"></div>
        <div class="foot-lnk">
          <label for="tab-1">Already Member?</label>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>