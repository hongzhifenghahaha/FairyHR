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
@RequestMapping("/")
public class HomeController {
    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    HomeController(UserService userService, DepartmentService departmentService){
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public String getHome()
    {
        return "sign";
        //homeService.getSchedulesString().get(0);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam(value = "id", defaultValue = "") String id,
                               @RequestParam(value = "password", defaultValue = "") String password,
                               @RequestParam(value = "manager", defaultValue = "")String manager,
                               HttpSession session, HttpServletResponse response) {
        User user =  userService.selectById(id);

        if (user!=null && user.getPassword()!=null){
            if(user.getPassword().equals(password)){
                session.setAttribute("id",id);
                session.setAttribute("userName",user.getName());
                session.setAttribute("position",user.getPosition());
                Cookie cookieId = new Cookie("id", id);
                Cookie cookieName = new Cookie("userName", user.getName());
                cookieId.setMaxAge(60*60*2);
                cookieName.setMaxAge(60*60*2);
                response.addCookie(cookieId);
                response.addCookie(cookieName);
                return "welcome";
            }
        }
        return "sign";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processSignUp(@RequestParam(value = "id", defaultValue = "") String id,
                                @RequestParam(value = "userName", defaultValue = "") String userName,
                                @RequestParam(value = "password", defaultValue = "")String password,
                                @RequestParam(value = "email",defaultValue = "")String email,
                                HttpSession session) {
        User user =  new User(id, userName, password, email);
        if(userService.selectById(id)==null){
            userService.insert(user);
        }
        return "sign";
    }


    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String gotoAbout(){
        return "about";
    }


}
