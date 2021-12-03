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

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    AttendanceController(UserService userService, DepartmentService departmentService){
        this.userService = userService;
        this.departmentService = departmentService;
    }

   @RequestMapping(value = "/checkin",method = RequestMethod.GET)
    public String getCheckInPage(){
        return "attend/checkin";
   }
}
