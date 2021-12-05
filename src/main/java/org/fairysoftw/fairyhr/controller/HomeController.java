package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.Department;
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

/**
 * 系统主页控制类
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final UserService userService;
    private final DepartmentService departmentService;

    /**
     * 构造函数，通过Spring自动装配。
     */
    @Autowired
    HomeController(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    /**
     * 首页
     */
    @GetMapping("/")
    public String getHome(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "welcome";
        //homeService.getSchedulesString().get(0);
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(HttpSession session) {
        session.setAttribute("msg", "");
        return "login";
    }

    /**
     * 登录请求
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String proccessLogin(@RequestParam(value = "userid", defaultValue = "") String userid,
                                @RequestParam(value = "password", defaultValue = "") String password,
                                HttpSession session,
                                HttpServletResponse response,
                                HttpServletRequest request) throws IOException {
        //如果原本有账号登录,退出账号
        if (session.getAttribute("id") != null) {
            session.removeAttribute("user");
            session.removeAttribute("id");
        }
        User user = userService.selectById(userid);

        if (user != null && user.getPassword() != null) {
            if (user.getPassword().equals(password)) {
                //todo:直接存user到session似乎不太好
                session.setAttribute("user", user);
                session.setAttribute("id", userid);
                Cookie cookieId = new Cookie("id", userid);
                Cookie cookieName = new Cookie("userName", user.getName());
                cookieId.setMaxAge(60 * 60 * 2);
                cookieName.setMaxAge(60 * 60 * 2);
                response.addCookie(cookieId);
                response.addCookie(cookieName);

                boolean isManager = false;
                for (Department d : departmentService.selectByUserId(userid)) {
                    for (User u : d.getManagers()) {
                        if (u.getId().equals(userid)) {
                            isManager = true;
                        }
                    }
                }
                if (isManager) {
                    session.setAttribute("position", "manager");
                } else {
                    session.setAttribute("position", "user");
                }
                return "redirect:/";
            }
        }
        request.setAttribute("msg", "user info is wrong, please check your input.");

        return "login";
    }

    /**
     * 错误页面
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "error";
    }

    /**
     * 处理登出操作
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String Logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("id");
        session.removeAttribute("department");
        session.removeAttribute("position");
        session.setAttribute("msg", "");
        return "login";
    }
}