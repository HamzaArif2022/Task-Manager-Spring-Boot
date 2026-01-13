package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher,Long> {
}
