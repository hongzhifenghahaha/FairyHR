package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.*;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class LeaveController {
    private final LeaveRequestService leaveRequestService;
    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    LeaveController(LeaveRequestService leaveRequestService, UserService userService,DepartmentService departmentService){
        this.leaveRequestService =leaveRequestService;
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/askForLeave", method = RequestMethod.GET)
    public String gotoAskForLeave()
    {
        return "leave/askForLeave";
    }

    @RequestMapping(value = "/leaveRecord", method = RequestMethod.GET)
    public String gotoLeaveRecord()
    {
        return "leave/leaveRecord";
    }

    @RequestMapping(value = "/reviewLeave", method = RequestMethod.GET)
    public String gotoLeaveRequest(HttpSession session)
    {
        /**TODO 此处遍历查新当前登录user的department，因为service层尚未提供获取当前user的Department的方法，后续service层增加后注意替换*/
        List<Department> departments = departmentService.selectAll();
        Department department = null;
        for(var departmentItem : departments ){
            for(var userItem :departmentItem.getUsers()){
                if(userItem.getId().equals(session.getAttribute("id"))){
                    department = departmentItem;
                }
            }
        }
        session.setAttribute("department",department);
        return "List/leaveRequest";
    }

    @RequestMapping(value = "/askForLeave", method = RequestMethod.POST)
    public String processAskForLeave(@RequestParam(value = "startDate", defaultValue = "")
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                     @RequestParam(value = "startTime", defaultValue = "")String startTime,
                                     @RequestParam(value = "endDate", defaultValue = "")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                     @RequestParam(value = "endTime", defaultValue = "")String endTime,
                                     @RequestParam(value = "reason", defaultValue = "") String reason,
                                     HttpSession session, HttpServletResponse response) throws IOException {

        if((String)session.getAttribute("id") == null){
            /*response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("alert('您尚未登录，请先登录');");
            return "sign";*/
        }
        List<LeaveRequest> leaveRequests = leaveRequestService.selectAll();
        long id = 0;
        for(var item : leaveRequests){
            if(Long.parseLong(item.getId())>id){
                id = Long.parseLong(item.getId())+1;
            }
        }
        LeaveRequest leaveRequest = new LeaveRequest(String.valueOf(id),
                userService.selectById((String)session.getAttribute("id")),
                startDate,endDate,new Date(), reason, "待审核" , null, null, null);
        leaveRequestService.insert(leaveRequest);

        Department department = (Department) session.getAttribute("department");
        if(department.getLeaveRequests()==null){
            ArrayList<LeaveRequest> myLeaveRequest = new ArrayList<LeaveRequest>();
            leaveRequests.add(leaveRequest);
            department.setLeaveRequests((List)myLeaveRequest);
        } else {
            department.getLeaveRequests().add(leaveRequest);
        }
        departmentService.update(department);

        return "leave/leaveRecord";
    }


    @RequestMapping(value = "/allowLeaveRequestItem", method = RequestMethod.POST)
    public String allowLeaveRequestItem(@RequestParam(value = "id", defaultValue = "")String id, HttpSession session){
        LeaveRequest leaveRequest = leaveRequestService.selectById(id);
        leaveRequest.setStatus("审核通过");
        leaveRequestService.update(leaveRequest);

        /**TODO 此处遍历查新当前登录user的department，因为service层尚未提供获取当前user的Department的方法，后续service层增加后注意替换*/
        List<Department> departments = departmentService.selectAll();
        Department department = null;
        for(var departmentItem : departments ){
            for(var userItem :departmentItem.getUsers()){
                if(userItem.getId().equals(session.getAttribute("id"))){
                    department = departmentItem;
                }
            }
        }


        departmentService.update(department);
        session.setAttribute("department",department);

        return "List/leaveRequest";
    }

    @RequestMapping(value = "/refuseLeaveRequestItem", method = RequestMethod.POST)
    public String refuseLeaveRequestItem(@RequestParam(value = "id", defaultValue = "")String id, HttpSession session){
        LeaveRequest leaveRequest = leaveRequestService.selectById(id);
        leaveRequest.setStatus("审核未通过");
        leaveRequestService.update(leaveRequest);

        /**TODO 此处遍历查新当前登录user的department，因为service层尚未提供获取当前user的Department的方法，后续service层增加后注意替换*/
        List<Department> departments = departmentService.selectAll();
        Department department = null;
        for(var departmentItem : departments ){
            for(var userItem :departmentItem.getUsers()){
                if(userItem.getId().equals(session.getAttribute("id"))){
                    department = departmentItem;
                }
            }
        }


        departmentService.update(department);
        session.setAttribute("department",department);

        return "List/leaveRequest";
    }


}
