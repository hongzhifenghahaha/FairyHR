package org.fairysoftw.fairyhr.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final UserService userService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @RequestMapping(method = GET)
    public String getDepartmentTree(HttpSession session, HttpServletRequest httpServletRequest) {
        var userId = (String) session.getAttribute("id");
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("class", "go.TreeModel");
        JSONArray jsonArray = new JSONArray();
        for (var department : departmentService.selectAll()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", department.getId());
            jsonObject.put("name", department.getName());
            jsonObject.put("enable", department.getManagers().stream().anyMatch((u) -> u.getId().equals(userId)));
            if (department.getDepartment() != null) {
                jsonObject.put("parent", department.getDepartment().getId());
            }
            jsonArray.add(jsonObject);
        }
        jsonModel.put("nodeDataArray", jsonArray);
        var model = jsonModel.toJSONString();
        httpServletRequest.setAttribute("model", model);
        departmentService.selectAll();

        return "department/department";
    }

    @RequestMapping(value = "/{id}", method = GET)
    public String getDepartment(@PathVariable(value = "id") String id, HttpSession session,
                                HttpServletRequest request) {
        Department department = departmentService.selectById(id);
        session.setAttribute("department", department);
        request.setAttribute("user_num", department.getUsers().size());
        request.setAttribute("leaves", department.getLeaveRequests());
        request.setAttribute("users", department.getUsers());
        request.setAttribute("managers", department.getManagers());
        return "department/manager";
    }

    @RequestMapping(value = "/register", method = GET)
    public String getRegisterPage() {

        return "user/register";
    }

    @RequestMapping(value = "/register", method = POST)
    public String addUser(@RequestParam(value = "id") String id, HttpSession session,
                          @RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "phone", defaultValue = "") String phone,
                          @RequestParam(value = "email", defaultValue = "") String email,
                          @RequestParam(value = "resident", defaultValue = "") String resident,
                          @RequestParam(value = "address", defaultValue = "") String address,
                          @RequestParam(value = "password", defaultValue = "") String password) {
        for (User u : userService.selectAll()) {
            if (u.getId().equals(id)) {
                session.setAttribute("msg", "the id has existed, please try another id.");
                return "user/register";
            }
        }
        Department department = (Department) session.getAttribute("department");
        User user = new User(id, name, phone, password, resident, email, address, null, null, null, null, null, false);
        department.getUsers().add(user);
        departmentService.update(department);
        return "redirect:/department/" + department.getId();
    }

    @RequestMapping(value = "/deleteUser", method = POST)
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(request.getParameter("user_id"));
        System.out.println(userService.selectById(request.getParameter("user_id")));
        User user = userService.selectById((String) session.getAttribute("id"));
        Department department = (Department) session.getAttribute("department");
        department.getUsers().removeIf((u) -> u.getId().equals(request.getParameter("user_id")));
        departmentService.update(department);
        request.setAttribute("user_num", department.getUsers().size());
        request.setAttribute("users", department.getUsers());
        request.setAttribute("managers", department.getManagers());
    }

    @RequestMapping(value = "/assignUser", method = POST)
    public void assignUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(request.getParameter("user_id"));
        System.out.println(userService.selectById(request.getParameter("user_id")));
        response.getWriter().write("success");
        Department department = (Department) session.getAttribute("department");
        department.getManagers().add(userService.selectById(request.getParameter("user_id")));
        departmentService.update(department);
        request.setAttribute("user_num", department.getUsers().size());
        request.setAttribute("users", department.getUsers());
        request.setAttribute("managers", department.getManagers());
    }

    @RequestMapping(value = "/addOldUser", method = GET)
    public String addOldUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        User user = userService.selectById((String) session.getAttribute("id"));
        Department department = (Department) session.getAttribute("department");
        List<User> our_d = department.getUsers();
        List<User> others = new ArrayList<>();
        for (User u : userService.selectAll()) {
            if (!our_d.contains(u)) {
                others.add(u);
            }
        }
        session.setAttribute("others", others);
        return "department/addOthers";
    }

    @RequestMapping(value = "/addOldUser", method = POST)
    public void addOldUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user_id = request.getParameter("user_id");
        User user = userService.selectById(user_id);
        Department department = (Department) session.getAttribute("department");
        department.getUsers().add(user);
        departmentService.update(department);
        List<User> others=(List<User>) session.getAttribute("others");
        others.remove(user);
        response.getWriter().write("complete");
    }

    @RequestMapping(value = "/changeName", method = POST)
    public String changeDepartmentName(@RequestParam(value = "de_name") String de_name, HttpSession session) throws IOException {
        Department department = (Department) session.getAttribute("department");
        department.setName(de_name);
        departmentService.update(department);
        return "redirect:/department/" + department.getId();
    }
}
