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
public class UserManager {
    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    UserManager(UserService userService, DepartmentService departmentService){
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String gotoUserList(HttpSession session){
        return "List/userList";
    }



}
