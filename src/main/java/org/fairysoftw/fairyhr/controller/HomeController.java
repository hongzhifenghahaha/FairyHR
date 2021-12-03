package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
        return "home";
        //homeService.getSchedulesString().get(0);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String proccessLogin(@RequestParam(value = "userid",defaultValue = "") String userid,
                                @RequestParam(value = "password",defaultValue = "") String password,
                                HttpSession session,
                                HttpServletResponse response) throws IOException {
        System.out.println(userid);
        System.out.println(password);
        User user =  userService.selectById(userid);

        if (user!=null && user.getPassword()!=null){
            if(user.getPassword().equals(password)){
                //todo:直接存user到session似乎不太好
                session.setAttribute("user",user);
                session.setAttribute("id",userid);
                Cookie cookieId = new Cookie("id", userid);
                Cookie cookieName = new Cookie("userName", user.getName());
                cookieId.setMaxAge(60*60*2);
                cookieName.setMaxAge(60*60*2);
                response.addCookie(cookieId);
                response.addCookie(cookieName);
                return "home";
            }
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('user info is wrong, please check your input.'); window.location='login' </script>");
        out.flush();
        out.close();
        return "login";
    }
}
