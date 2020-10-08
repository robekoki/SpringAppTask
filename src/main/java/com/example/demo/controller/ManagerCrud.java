package com.example.demo.controller;

import com.example.demo.model.Manager;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManagerCrud {

    @Autowired
    ProjectService service;

    @GetMapping("/projects/newManager")
    public String createManager(Model model) {
        model.addAttribute("manager", new Manager());
        return "newManager";
    }

    @PostMapping("/projects/newManager")
    public String managerSubmit(@ModelAttribute Manager manager, Model model) {
        model.addAttribute("manager", manager);
        service.save(manager);
        return "createManagerSuccess";
    }

    @GetMapping("/projects/showAllManagers")
    public String showAllManagers(Model model) {
        model.addAttribute("managers", service.getAllManagers());
        return "showAllManagers";
    }
}
