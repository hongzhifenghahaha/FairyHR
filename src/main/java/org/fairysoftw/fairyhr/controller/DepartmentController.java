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
import java.util.Date;
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

        List<Department> subs = new ArrayList<>();
        for (Department d : departmentService.selectAll()) {
            if (d.getDepartment() != null && d.getDepartment().equals(department)) {//如果d是本部门的下级部门
                subs.add(d);
            }
        }
        request.setAttribute("deletableDepartments", subs);
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

        List<User> rovers = new ArrayList<>();
        for (User u : userService.selectAll()) {
            if (departmentService.selectByUserId(u.getId()).isEmpty()) {
                rovers.add(u);
            }
        }
        session.setAttribute("rovers", rovers);
        return "department/addOthers";
    }

    @RequestMapping(value = "/addOldUser", method = POST)
    public void addOldUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user_id = request.getParameter("user_id");
        User user = userService.selectById(user_id);
        Department department = (Department) session.getAttribute("department");
        department.getUsers().add(user);
        departmentService.update(department);
        List<User> others = (List<User>) session.getAttribute("others");
        others.remove(user);
        response.getWriter().write("complete");
    }

    @RequestMapping(value = "/deleteRover", method = POST)
    public void deleteRoverUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user_id = request.getParameter("user_id");
        userService.deleteById(user_id);
        response.getWriter().write("complete");
    }

    @RequestMapping(value = "/changeName", method = POST)
    public String changeDepartmentName(@RequestParam(value = "de_name") String de_name, HttpSession session) throws IOException {
        Department department = (Department) session.getAttribute("department");
        department.setName(de_name);
        departmentService.update(department);
        return "redirect:/department/" + department.getId();
    }

    @RequestMapping(value = "/passRequest", method = POST)
    public void passRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        var manager_id = (String) session.getAttribute("id");
        var request_id = (String) request.getParameter("request_id");
        var department = departmentService.selectById(
                ((Department) session.getAttribute("department")).getId());
        if (department != null && department.getLeaveRequests() != null) {
            var leaveRequests = department.getLeaveRequests()
                    .stream().filter((l) -> l.getId().equals(request_id))
                    .toList();
            for (var leaveRequest : leaveRequests) {
                leaveRequest.setChecker(userService.selectById(manager_id));
                leaveRequest.setCheckTime(new Date());
                leaveRequest.setStatus("审核通过");
                leaveRequest.setCheckOpinion("TODO: 设置审核意见");
            }
            departmentService.update(department);
        }
        response.getWriter().write("success");
    }

    @RequestMapping(value = "/rejectRequest", method = POST)
    public void rejectRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        var manager_id = (String) session.getAttribute("id");
        var request_id = (String) request.getParameter("request_id");
        var department = departmentService.selectById(
                ((Department) session.getAttribute("department")).getId());
        if (department != null && department.getLeaveRequests() != null) {
            var leaveRequests = department.getLeaveRequests()
                    .stream().filter((l) -> l.getId().equals(request_id))
                    .toList();
            for (var leaveRequest : leaveRequests) {
                leaveRequest.setChecker(userService.selectById(manager_id));
                leaveRequest.setCheckTime(new Date());
                leaveRequest.setStatus("审核不通过");
                leaveRequest.setCheckOpinion("TODO: 设置审核意见");
            }
            departmentService.update(department);
        }
        response.getWriter().write("success");
    }

    @RequestMapping(value = "/delete", method = POST)
    public void deleteSubDepartment(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String department_id = request.getParameter("department_id");
        System.out.println(department_id);
        departmentService.deleteById(department_id);
        response.getWriter().write("complete");
    }

    //必须输入不能重复的姓名和id 必须选择一个新管理员
    @RequestMapping(value = "/add", method = POST)
    public String addSubDepartment(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String[] new_managers = request.getParameterValues("new_managers");

        //判断
        boolean create = true;
        for (Department d : departmentService.selectAll()) {
            if (d.getId().equals(id)) {
                session.setAttribute("msg", "the department id has existed, please try another id.");
                create = false;
            }
        }
        for (Department d : departmentService.selectAll()) {
            if (d.getName().equals(name)) {
                session.setAttribute("msg", "the department name has existed, please try another name.");
                create = false;
            }
        }

        if (!create) {
            return "redirect:/department/add";
        }
        List<User> managers = new ArrayList<>();
        Department father = ((Department) session.getAttribute("department"));
        for (User u : father.getManagers()) {
            managers.add(u);
        }
        if (new_managers != null) {
            for (String m_id : new_managers) {//更新管理员职位
                User u = userService.selectById(m_id);
                u.setPosition(u.getPosition() + " " + name + "长");
                userService.update(u);
                managers.add(u);
            }
        }
        departmentService.insert(new Department(id, name, father, false, managers, managers, null));
        return "redirect:/department/" + father.getId();
    }

    @RequestMapping(value = "/add", method = GET)
    public String getAddSubDepartmentPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> candidates = new ArrayList<>();
        Department father = ((Department) session.getAttribute("department"));
        for (User u : userService.selectAll()) {
            boolean isExist = false;
            for (User m : father.getManagers()) {
                if (m.getId().equals(u.getId())) {
                    isExist = true;
                }
            }
            if (!isExist){
                candidates.add(u);
            }
        }
        for (User u : candidates) {
            System.out.println(u);
        }
        request.setAttribute("candidates", candidates);
        return "department/addDepartment";
    }
}
