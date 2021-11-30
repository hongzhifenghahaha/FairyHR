<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>FairyHR</title>
    <link rel="stylesheet" type="text/css" href="/static/checkIn.css">
    <style>
    #main {
    margin: auto;
    text-align: center;
    width: 300px;
    height: 200px;
    background-color: #0CC;
    }
    #show_time0,#show_time {
        width:300px;
        height:100px;
        color: #111;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    </style>

</head>
<body>
    <c:import url="../navigationBar.jsp"/>

    <div id="show_time">
        <script>
            setInterval("fun(show_time)",1);
            function fun(timeID){
                var date = new Date();  //创建日期对象
                var y = date.getFullYear();
                var m =date.getMonth()+1;
                var d = date.getDate();
                var w = date.getDay();
                var ww = ' 星期'+'日一二三四五六'.charAt(new Date().getDay()) ;
                var h = date.getHours();
                var minute = date.getMinutes();
                var s = date.getSeconds();
                var sss = date.getMilliseconds() ;
                if(m<10){
                    m = "0"+m;
                }
                if(d<10){
                    d = "0"+d;
                }
                if(h<10){
                    h = "0"+h;
                }


                if(minute<10){
                    minute = "0"+minute;
                }


                if(s<10){
                    s = "0"+s;
                }


                if(sss<10){
                    sss = "00"+sss;
                }else if(sss<100){
                    sss = "0"+sss;
                }


                document.getElementById(timeID.id).innerHTML =  y+"-"+m+"-"+d+"   "+h+":"+minute+":"+s+"  "+ww;
            }
        </script>
    </div>
    <main>
        <form action="/myCheckIn" method="get">
        <input id="select" type="submit" class="button"/>
        <label for="select"><span>Submit Your Attendance</span><span>Working...</span></label>
        </form>
    </main>
</body>
</html>
