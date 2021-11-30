package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.http.HttpCookie;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DepartmentController {
    private final DepartmentService departmentService;
    private final UserService userService;

    @Autowired
    DepartmentController(DepartmentService departmentService, UserService userService){
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public String gotoHomePage(HttpSession session){
        String id = (String) session.getAttribute("id");

        List<Department> departments = departmentService.selectAll();
        Department department = null;
        for(var departmentItem : departments ){
            for(var userItem :departmentItem.getUsers()){
                if(userItem.getId().equals(id)){
                    department = departmentItem;
                }
            }
        }

        if(department != null){
            session.setAttribute("department",department);
            System.out.println(department.getManagers().size());
            for (var item : department.getManagers()){
                if(item.getId().equals(id)){
                    session.setAttribute("manager",item);
                }
            }
        }
        return "homePage";
    }


}
