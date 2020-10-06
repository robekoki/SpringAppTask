package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public Project(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public Project() {

    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", manager=" + manager +
                '}';
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
