package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.AttendanceTime;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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

    @RequestMapping(value = "/departmentDeleteUser", method = RequestMethod.POST)
    public String departmentDeleteUser(@RequestParam(value = "id", defaultValue = "")String id, HttpSession session){
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

        if(department != null){
            Iterator<User> iterator = department.getUsers().iterator();
            while(iterator.hasNext()){
                User user = iterator.next();
                if(user.getId().equals(id)){
                    iterator.remove();
                }
            }

            Iterator<User> iterator2 = department.getManagers().iterator();
            while(iterator.hasNext()){
                User manager = iterator.next();
                if(manager.getId().equals(id)){
                    iterator.remove();
                }
            }
        }

        departmentService.update(department);
        session.setAttribute("department",department);

        return "List/userList";
    }


    @RequestMapping(value = "/departmentAddUser", method = RequestMethod.POST)
    public String departmentAddUser(@RequestParam(value = "id", defaultValue = "")String id, HttpSession session){
        List<Department> departments = departmentService.selectAll();
        Department department = null;
        for(var departmentItem : departments ){
            for(var userItem :departmentItem.getUsers()){
                if(userItem.getId().equals(session.getAttribute("id"))){
                    department = departmentItem;
                }
            }
        }

        if(department.getUsers()==null){
            ArrayList<User> users = new ArrayList<User>();
            users.add(userService.selectById(id));
            department.setUsers(users);
        } else {
            department.getUsers().add(userService.selectById(id));
        }
        departmentService.update(department);
        session.setAttribute("department",department);

        return "List/userList";
    }

}
