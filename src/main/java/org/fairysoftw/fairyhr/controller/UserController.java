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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    UserController(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String getProfilePage(@PathVariable(value = "id") String id, HttpServletRequest request) {
        request.setAttribute("profile_user", userService.selectById(id));
        return "user/profile";
    }

    @RequestMapping(value = "/profile/update/{id}", method = RequestMethod.GET)
    public String getUpdatePage(@PathVariable(value = "id") String id, HttpServletRequest request) {
        request.setAttribute("profile_user", userService.selectById(id));
        return "user/update";
    }

    @RequestMapping(value = "/profile/update/{id}", method = RequestMethod.POST)
    public String updateProfile(@PathVariable(value = "id") String id, HttpServletRequest request,
                                @RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "phone", defaultValue = "") String phone,
                                @RequestParam(value = "email", defaultValue = "") String email,
                                @RequestParam(value = "resident", defaultValue = "") String resident,
                                @RequestParam(value = "address", defaultValue = "") String address,
                                @RequestParam(value = "password", defaultValue = "") String password,
                                HttpSession session) throws IOException {
        User user = userService.selectById(id);
        user.setName(name);
        user.setPhoneNumber(phone);
        user.setEmailAddr(email);
        user.setResidentId(resident);
        user.setAddress(address);
        user.setPassword(password);
        userService.update(user);
        if ((User) session.getAttribute("user") != null && user.getId().equals(((User) session.getAttribute("user")).getId())) {
             session.setAttribute("user",user);
        }
        return "redirect:/user/profile/" + id;

    }

}
