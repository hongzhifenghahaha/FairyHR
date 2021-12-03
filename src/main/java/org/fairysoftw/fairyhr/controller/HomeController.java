package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.User;
import org.springframework.stereotype.Controller;
import org.fairysoftw.fairyhr.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import java.io.IOException;

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

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String getHome()
    {
        return "home";
        //homeService.getSchedulesString().get(0);
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String proccessLogin(@RequestParam(value = "userid") String userid,
                                @RequestParam(value = "password") String password){

        User user =  userService.selectById(id);

        if (user!=null && user.getPassword()!=null){
            if(user.getPassword().equals(password)){
                //todo:直接存user到session似乎不太好
                session.setAttribute("user",user);
                session.setAttribute("id",id);
                session.setAttribute("userName",user.getName());
                session.setAttribute("position",user.getPosition());
                Cookie cookieId = new Cookie("id", id);
                Cookie cookieName = new Cookie("userName", user.getName());
                cookieId.setMaxAge(60*60*2);
                cookieName.setMaxAge(60*60*2);
                response.addCookie(cookieId);
                response.addCookie(cookieName);
                return "homePage";
            }
        }
        return "home";
    }
}
