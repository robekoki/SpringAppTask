package com.example.demo.service;

import com.example.demo.model.Manager;
import com.example.demo.model.Project;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectService {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    ManagerRepository managerRepo;

    public void save(Project project){
        projectRepo.save(project);
    }

    public List<Manager> getAvailableManagers() {
        List<Manager> list = (List<Manager>) managerRepo.findAll();
        return list.stream()
                .filter(x -> x.getProject() == null)
                .collect(Collectors.toList());
    }

    public List<Project> getAllProjects() {
        return (List<Project>) projectRepo.findAll();
    }

    public Optional<Project> getById(int projectId) {
        return projectRepo.findById(projectId);
    }

    public void delete(int id) {
        Optional<Project> project = projectRepo.findById(id);
        project.ifPresent(value -> value.setManager(null));
        projectRepo.deleteById(id);
    }
}
