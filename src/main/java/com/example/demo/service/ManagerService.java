package com.example.demo.service;

import com.example.demo.model.Manager;
import com.example.demo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ManagerService {

    @Autowired
    ManagerRepository repo;

    public Optional<Manager> getById(int id) {
        return repo.findById(id);
    }

    public void save(Manager manager) {
        repo.save(manager);
    }

    public List<Manager> getAllManagers() {
        return (List<Manager>) repo.findAll();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

}
