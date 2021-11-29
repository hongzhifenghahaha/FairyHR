package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class LeaveController {
    @RequestMapping(value = "/askForLeave", method = RequestMethod.GET)
    public String gotoAskForLeave()
    {
        return "askForLeave";
    }

    @RequestMapping(value = "/leaveRecord", method = RequestMethod.GET)
    public String gotoLeaveRecord()
    {
        return "leaveRecord";
    }
}
