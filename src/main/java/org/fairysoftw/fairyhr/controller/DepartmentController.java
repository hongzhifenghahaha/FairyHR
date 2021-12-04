package org.fairysoftw.fairyhr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @RequestMapping(method = GET)
    public String getHome() {
        return "department";
    }
}
