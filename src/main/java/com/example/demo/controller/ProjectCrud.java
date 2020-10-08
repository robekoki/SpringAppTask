package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.Manager;
import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


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

    @GetMapping("/projects/showAllProjects")
    public String showAllProjects(Model model) {
        model.addAttribute("projects", service.getAllProjects());
        return "showAllProjects";
    }

    @GetMapping("/projects/getProject")
    public String getProject(Model model) {
        model.addAttribute("project", new Project());
        return "getProject";
    }

    @PostMapping("/projects/getProject")
    public String showProject(@ModelAttribute Project project, Model model) {
        Optional<Project> optional = service.getById(project.getProjectId());
        if (optional.isPresent()) {
            model.addAttribute("project", optional.get());
            //tempItem = optional.get();
            return "/showProject";
        } else {
            return "/noProjectFound";
        }
    }

    @PostMapping("/projects/deleteProject")
    public String deleteProject(@RequestParam int id, Model model) {
        service.delete(id);
        return "projectDeleted";
    }

    @GetMapping("/projects/updateProject")
    public String updateProject(Model model) {

        return "updateProject";
    }
}
