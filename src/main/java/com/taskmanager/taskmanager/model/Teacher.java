package com.taskmanager.taskmanager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    //many subject can teached by one teacher
    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects = new ArrayList<>();

    // one teacher have a one profile Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private TeacherProfile profile;
}
