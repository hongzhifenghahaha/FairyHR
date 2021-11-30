package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.fairysoftw.fairyhr.model.LeaveRequest;
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
import java.util.Date;
import java.util.List;

@Controller
public class LeaveController {
    private final LeaveRequestService leaveRequestService;
    private final UserService userService;

    @Autowired
    LeaveController(LeaveRequestService leaveRequestService, UserService userService){
        this.leaveRequestService =leaveRequestService;
        this.userService = userService;
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
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("alert('您尚未登录，请先登录');");
            return "sign";
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
                startDate,endDate,new Date(), reason, "unreviewed" , null, null, null);
        leaveRequestService.insert(leaveRequest);

        return "leave/leaveRecord";
    }
}
