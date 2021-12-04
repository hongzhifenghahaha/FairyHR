package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public String getHome(HttpSession session)
    {
        if (session.getAttribute("id")==null){
            return "redirect:/login";
        }
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
                                HttpServletResponse  response,
                                HttpServletRequest request) throws IOException {
        //如果原本有账号登录,退出账号
        if (session.getAttribute("id")!=null){
            session.removeAttribute("user");
            session.removeAttribute("id");
        }
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
                return "redirect:/";
            }
        }
        request.setAttribute("msg","user info is wrong, please check your input.");

        return "login";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String getErrorPage(){
        return "nonuse";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String Logout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("id");
        return "login";
    }
}
