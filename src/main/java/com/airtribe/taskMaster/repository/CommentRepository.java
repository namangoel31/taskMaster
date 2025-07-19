package com.airtribe.taskMaster.repository;

import com.airtribe.taskMaster.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Project, Long> {
}
