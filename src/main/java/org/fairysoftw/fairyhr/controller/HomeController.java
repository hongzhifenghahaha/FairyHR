package org.fairysoftw.fairyhr.controller;

import org.fairysoftw.fairyhr.model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String getHome()
    {
        return "index";
        //homeService.getSchedulesString().get(0);
    }
}
