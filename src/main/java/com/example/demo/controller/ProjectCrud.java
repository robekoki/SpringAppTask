package com.example.demo.controller;

import com.example.demo.model.Manager;
import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProjectCrud {

    @Autowired
    ProjectService service;

    @GetMapping("/projects")
    public String projects() {
        return "projects";
    }

    @GetMapping("/projects/newProject")
    public String createProject(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("managers", service.getAvailableManagers());
        return "newProject";
    }

    @PostMapping("/projects/newProject")
    public String projectSubmit(@ModelAttribute Project project, Model model) {
        model.addAttribute("project", project);
        if(project.getManager().getId() == 0) {
            project.setManager(null);
        }
        service.save(project);
        return "createProjectSuccess";
    }

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

    @GetMapping("/projects/showAllProjects")
    public String showAllProjects(Model model) {
        model.addAttribute("projects", service.getAllProjects());
        return "showAllProjects";
    }
}
