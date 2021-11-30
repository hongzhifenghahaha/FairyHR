package org.fairysoftw.fairyhr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeaveController {
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
}
