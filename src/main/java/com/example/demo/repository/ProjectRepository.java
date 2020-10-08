package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    @Query(value = "select * from project where manager_id = ?1", nativeQuery = true)
    Project getByFreeKey(int manager_id);
}
