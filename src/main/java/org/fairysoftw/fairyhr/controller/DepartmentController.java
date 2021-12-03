package org.fairysoftw.fairyhr.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
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

        return "department";
    }

    @RequestMapping(value = "/{id}", method = GET)
    public String getDepartment(@PathVariable(value = "id") String id) {
        return "department";
    }
}
