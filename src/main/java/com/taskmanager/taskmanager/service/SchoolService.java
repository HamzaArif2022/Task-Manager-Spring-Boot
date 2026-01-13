package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.model.Student;
import com.taskmanager.taskmanager.model.Subject;
import com.taskmanager.taskmanager.repository.StudentRepository;
import com.taskmanager.taskmanager.repository.SubjectRepository;
import com.taskmanager.taskmanager.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private  SubjectRepository subjectRepository;

    public Student enrollStudent(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        student.getSubjects().add(subject);

        return studentRepository.save(student);
    }


}
