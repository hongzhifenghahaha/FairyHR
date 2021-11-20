<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>用户登录界面</title>
  <script type="text/javascript">
    function changeCode(){
      //获取验证码元素
      var checkcode = document.getElementById("checkcode");
      checkcode.src="/UserManage/CodeServlet?time="+new Date().getTime();
    }
  </script>
</head>
<body>
<form action="/UserManage/UserLoginServlet" method="post">
  <table align="center" border="1" style="border-collapse:collapse;">
    <tr>
      <td colspan="4" align="center">用户登录</td>
    </tr>
    <tr height="40px">
      <td>用户账户：</td>
      <td colspan="3"><input type="text" name="userAccount" size="20" align="left"></td>
    </tr>
    <tr height="40px">
      <td >用户密码：</td>
      <td colspan="3"><input type="password" name="userPassword" size="20" align="left"></td>
    </tr>
    <!-- </table>
    <table align="center"> -->
    <!-- </table>
    <table align="center">	-->
    <tr>
      <td  colspan="4" align="center">
        <input type="submit" value="登录">
        <input type="button" value="注册"
               onclick="window.location.href='/UserManage/userregister.jsp'">
        <a href="/UserManage/RecoverPWDServlet"><font >忘记密码？</font></a>
      </td>
    </tr>
  </table>
  <table align="center">
    <tr>
      <td align="center"><font color="RED">
        <%
          String UDmsg = (String) request.getAttribute("UDmsg");
          String usermsg = (String) request.getAttribute("usermsg");
          String codemsg = (String) request.getAttribute("codemsg");
          if(UDmsg != null){
            out.println(UDmsg);
          }else if(usermsg != null){
            out.println(usermsg);
          }else if(codemsg != null){
            out.println(codemsg);
          }
        %>
      </font></td>
    </tr>
  </table>
  <br>
</form>
</body>
</html>