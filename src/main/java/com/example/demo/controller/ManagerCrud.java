package com.example.demo.controller;

import com.example.demo.model.Manager;
import com.example.demo.service.ManagerService;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ManagerCrud {

    @Autowired
    ManagerService managerService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects/newManager")
    public String createManager(Model model) {
        model.addAttribute("manager", new Manager());
        return "newManager";
    }

    @PostMapping("/projects/newManager")
    public String managerSubmit(@ModelAttribute Manager manager, Model model) {
        model.addAttribute("manager", manager);
        managerService.save(manager);
        return "createManagerSuccess";
    }

    @GetMapping("/projects/showAllManagers")
    public String showAllManagers(Model model) {
        List<Manager> list = managerService.getAllManagers();
        list.remove(0);
        model.addAttribute("managers", list);
        return "showAllManagers";
    }

    @GetMapping("/projects/getManager")
    public String getManager(Model model) {
        model.addAttribute("manager", new Manager());
        return "getManager";
    }

    @PostMapping("/projects/getManager")
    public String showManager(@ModelAttribute Manager manager, Model model) {
        Optional<Manager> optional = managerService.getById(manager.getId());
        if (optional.isPresent()) {
            model.addAttribute("manager", optional.get());
            return "/showManager";
        } else {
            return "/noManagerFound";
        }
    }

    @PostMapping("/projects/deleteManager")
    public String deleteManager(@RequestParam int id, Model model) {
        projectService.freeProject(id);
        managerService.delete(id);
        return "managerDeleted";
    }

    @PostMapping("/projects/updateManager")
    public String updateManager(@RequestParam int id, Model model) {
        Optional<Manager> project = managerService.getById(id);
        project.ifPresent(value -> model.addAttribute("manager", value));
        return "updateManager";
    }

    @PostMapping("/projects/updatedManager")
    public String managerUpdated(@ModelAttribute Manager manager, @RequestParam int id, Model model) {
        manager.setId(id);
        managerService.save(manager);
        return "managerUpdated";
    }
}
