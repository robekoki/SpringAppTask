package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectService {

    @Autowired
    ProjectRepository repo;

    public void saveProject(Project project){
        repo.save(project);
    }

}
