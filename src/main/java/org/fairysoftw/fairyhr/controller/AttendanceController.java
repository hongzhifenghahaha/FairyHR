package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AttendanceController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    AttendanceController(UserService userService, DepartmentService departmentService){
        this.userService = userService;
        this.departmentService = departmentService;
    }

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
        User user = userService.selectById((String) session.getAttribute("id"));
        if(user.getAttendanceTimes()==null){
            ArrayList<AttendanceTime> times = new ArrayList<AttendanceTime>();
            times.add(new AttendanceTime());
            user.setAttendanceTimes(times);
        } else {
            user.getAttendanceTimes().add(new AttendanceTime());
        }
        userService.update(user);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.flush();
        out.println("alert('签到成功');");
        return "attendance/attendanceRecord";
    }

    @RequestMapping(value = "/attendanceRecord", method = RequestMethod.GET)
    public String gotoAttendanceRecord()
    {
        return "attendance/attendanceRecord";
    }

}
