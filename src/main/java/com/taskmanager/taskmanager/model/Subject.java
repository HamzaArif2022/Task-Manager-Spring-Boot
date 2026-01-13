package com.taskmanager.taskmanager.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    // Many students <-> Many subjects // this will generate a new pivot table caled student_subject
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;



    // One teacher teaches many subjects
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;



}
