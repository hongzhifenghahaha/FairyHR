package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.*;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
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

@Controller
@RequestMapping("/leave")
public class LeaveRequestController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    LeaveRequestController(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public String getLeaveRecordPage(HttpSession session) {
        //添加leave list to jsp
        User user = userService.selectById((String) session.getAttribute("id"));
        List<LeaveRequest> leaveRequestList = user.getLeaveRequests();

        List<LeaveRequest> unchecked = new ArrayList<>();
        List<LeaveRequest> checked = new ArrayList<>();

        for (LeaveRequest lr : leaveRequestList) {
            if (lr.getChecker() == null) {
                unchecked.add(lr);
            } else {
                checked.add(lr);
            }
        }

        session.setAttribute("checked_request", checked);
        session.setAttribute("unchecked_request", unchecked);
        session.setAttribute("no_add", "0");
        return "leave/leaveRecords";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddLeavePage(HttpSession session) {
        User user = userService.selectById((String) session.getAttribute("id"));
        List<Department> departmentList = departmentService.selectByUserId(user.getId());
        List<String> departs = new ArrayList<>();
        for (Department d : departmentList) {
            departs.add(d.getId());
        }
        session.setAttribute("departs", departs);
        return "leave/addLeave";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addLeaveRequest(@RequestParam(value = "start_date", defaultValue = "") String start_date,
                                  @RequestParam(value = "end_date", defaultValue = "00:00") String end_date,
                                  @RequestParam(value = "start_time", defaultValue = "") String start_time,
                                  @RequestParam(value = "end_time", defaultValue = "00:00") String end_time,
                                  @RequestParam(value = "depart", defaultValue = "") String depart,
                                  @RequestParam(value = "reason", defaultValue = "") String reason,
                                  HttpSession session) throws ParseException {
        if (start_date.length() < 2 || end_date.length() < 2 || start_time.length() < 2 || end_time.length() < 2) {
            session.setAttribute("msg", "please fill all the time box.");
            return "leave/addLeave";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd/HH:mm");
        Date date1 = df.parse(start_date + "/" + start_time);
        Date date2 = df.parse(end_date + "/" + end_time);
        if (date2.before(new Date()) && date1.after(date2)) {
            session.setAttribute("msg", "be sure the start_time is earlier than end_time and end_time is later than now the time.");
            return "leave/addLeave";
        }
        /*更新数据库 depart depart=待更新数据库*/
        Department department = departmentService.selectById(depart);
        LeaveRequest leaveRequest = new LeaveRequest(UUID.randomUUID().toString(),
                userService.selectById((String) session.getAttribute("id")),
                date1, date2, new Date(), reason, "公事", "待审核", null, null, null);//TODO: 请假类型
        if (department.getLeaveRequests() == null) {
            ArrayList<LeaveRequest> myLeaveRequest = new ArrayList<LeaveRequest>();
            myLeaveRequest.add(leaveRequest);
            department.setLeaveRequests((List) myLeaveRequest);
        } else {
            department.getLeaveRequests().add(leaveRequest);
        }
        departmentService.update(department);
        return "redirect:/leave/record";
    }

    @RequestMapping(value = "/record/{id}", method = RequestMethod.GET)
    public String getLeaveRecordPage(@PathVariable(value = "id") String id, HttpSession session, HttpServletRequest request) {
        //添加leave list to jsp
        User user = userService.selectById(id);
        List<LeaveRequest> leaveRequestList = user.getLeaveRequests();

        List<LeaveRequest> unchecked = new ArrayList<>();
        List<LeaveRequest> checked = new ArrayList<>();

        for (LeaveRequest lr : leaveRequestList) {
            if (lr.getChecker() == null) {
                unchecked.add(lr);
            } else {
                checked.add(lr);
            }
        }

        session.setAttribute("checked_request", checked);
        session.setAttribute("unchecked_request", unchecked);

        session.setAttribute("no_add","1");
        request.setAttribute("user_checked",user);
        return "leave/leaveRecords";
    }


}
