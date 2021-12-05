package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 考勤主页控制类
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final UserService userService;
    private final DepartmentService departmentService;

    /**
     * 构造函数，通过Spring自动装配
     */
    @Autowired
    AttendanceController(UserService userService, DepartmentService departmentService){
        this.userService = userService;
        this.departmentService = departmentService;
    }

    /**
     * 获取考勤页面
     */
    @RequestMapping(value = "/checkin",method = RequestMethod.GET)
    public String getCheckInPage(HttpSession session){
        User user = userService.selectById((String) session.getAttribute("id"));
        List<AttendanceTime> today_checkin=new ArrayList<>();
        for (AttendanceTime at:user.getAttendanceTimes()){
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            String dates=df.format(at.getTime());
            if (dates.equals(df.format(new Date()))){
                today_checkin.add(at);
            }
        }
        session.setAttribute("today_checkin",today_checkin);
        return "attend/checkin";
    }

    /**
     * 处理考勤请求
     */
    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    public String userCheckIn(HttpSession session, HttpServletResponse response,HttpServletRequest request) throws IOException {//不作跳转
        User user = userService.selectById((String) session.getAttribute("id"));
        if (user.getAttendanceTimes() == null) {
            ArrayList<AttendanceTime> times = new ArrayList<AttendanceTime>();
            times.add(new AttendanceTime(new Date()));
            user.setAttendanceTimes(times);
        } else {
            user.getAttendanceTimes().add(new AttendanceTime(new Date()));
        }
        userService.update(user);
        List<AttendanceTime> today_checkin=new ArrayList<>();
        for (AttendanceTime at:user.getAttendanceTimes()){
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            String dates=df.format(at.getTime());
            if (dates.equals(df.format(new Date()))){
                today_checkin.add(at);
            }
        }
        session.setAttribute("today_checkin",today_checkin);
        return "redirect:/attendance/checkin";
    }

    /**
     * 记录考勤
     */
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public String gotoAttendanceRecord(HttpSession session) {

        User user = userService.selectById((String) session.getAttribute("id"));
//        System.out.println(user);
        List<String[]> attendences = new ArrayList<>();
        List<User> managingUser = new ArrayList<>();

        if (user != null) {
            user = userService.selectById(user.getId());
            if (user.getAttendanceTimes() != null) {
                for (AttendanceTime at : user.getAttendanceTimes())
                    attendences.add(new String[]{user.getId(), user.getName(),
                            new SimpleDateFormat("yyyy-MM-dd").format(at.getTime()),
                            new SimpleDateFormat("HH:mm:ss").format(at.getTime())});
            }//改为十二小时制

        }
        session.setAttribute("attendances", attendences);

        return "attend/attendanceRecords";
    }

    /**
     * 处理特定用户的考勤
     */
    @RequestMapping(value = "/record/{id}", method = RequestMethod.GET)
    public String gotoAttendanceRecordByUserID(@PathVariable(value = "id")String id, HttpSession session) {

        User user = userService.selectById(id);
//        System.out.println(user);
        List<String[]> attendences = new ArrayList<>();
        List<User> managingUser = new ArrayList<>();

        if (user != null) {
            user = userService.selectById(user.getId());
            if (user.getAttendanceTimes() != null) {
                for (AttendanceTime at : user.getAttendanceTimes())
                    attendences.add(new String[]{user.getId(), user.getName(),
                            new SimpleDateFormat("yyyy-MM-dd").format(at.getTime()),
                            new SimpleDateFormat("HH:mm:ss").format(at.getTime())});
            }//改为十二小时制

        }
        session.setAttribute("attendances", attendences);

        return "attend/attendanceRecords";
    }
}