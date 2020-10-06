package com.example.demo.controller;


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
        return "newProject";
    }

    @PostMapping("/projects/newProject")
    public String projectSubmit(@ModelAttribute Project project, Model model) {
        model.addAttribute("project", project);
        service.saveProject(project);
        return "createProjectSuccess";
    }
}
