package org.fairysoftw.fairyhr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;

@Controller
public class AttendanceController {
    @RequestMapping(value = "/checkIn", method = RequestMethod.GET)
    public String gotoCheckIn()
    {
        return "attendance/checkIn";
    }

    @RequestMapping(value = "/myCheckIn", method = RequestMethod.GET)
    public String processMyCheckIn(HttpSession session, HttpServletResponse response) throws IOException {
        if(session.getAttribute("id")==null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("alert('您尚未登录，请先登录');");
            return "sign";
        }

        return "attendanceRecord";
    }

    @RequestMapping(value = "/attendanceRecord", method = RequestMethod.GET)
    public String gotoAttendanceRecord()
    {
        return "attendance/attendanceRecord";
    }

}
