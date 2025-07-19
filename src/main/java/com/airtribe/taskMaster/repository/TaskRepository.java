package com.airtribe.taskMaster.repository;

import com.airtribe.taskMaster.entity.Project;
import com.airtribe.taskMaster.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
}
