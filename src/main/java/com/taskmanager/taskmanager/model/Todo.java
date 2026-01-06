package com.taskmanager.taskmanager.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
@Entity
@Table(name = "todos")
public class Todo  {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id ;
        @Column(nullable = false)
         private  String title;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;



    public  Todo (String title, boolean completed, String description) {

        this.title = title;
        this.completed = completed;
        this.description = description;
    }

    public Todo() {

    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    @PreRemove
    public void onDelete() {
        this.deletedAt = LocalDateTime.now();
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
        private boolean   completed;

    public String getDescription() {
        return description;
    }

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}