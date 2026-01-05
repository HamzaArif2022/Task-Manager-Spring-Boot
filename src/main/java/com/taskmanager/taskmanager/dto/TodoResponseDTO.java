package com.taskmanager.taskmanager.dto;



// this TodoResponseDTO iwant responsable for filtring and hidding and showing some fields from  the payload response

public class TodoResponseDTO {
    public TodoResponseDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public TodoResponseDTO(Boolean completed, String title, Long id) {
        this.completed = completed;
        this.title = title;
        this.id = id;
    }

    private Long id;
    private String title;
    private Boolean completed;
    private String description;

}
