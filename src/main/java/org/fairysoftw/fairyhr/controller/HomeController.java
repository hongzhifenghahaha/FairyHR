package org.fairysoftw.fairyhr.controller;

import org.springframework.stereotype.Controller;
import org.fairysoftw.fairyhr.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String getHome()
    {
        return "sign";
        //homeService.getSchedulesString().get(0);
    }
}
