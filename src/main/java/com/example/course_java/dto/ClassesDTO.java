package com.example.course_java.dto;

public class ClassesDTO {
    private Long id;
    private String name;
    private String description;
    private Integer duration;
    private Long trainers_id;
    private Long gyms_id;

    public ClassesDTO() {
    }

    public ClassesDTO(Long id, String name, String description, Integer duration, Long trainers_id, Long gyms_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.trainers_id = trainers_id;
        this.gyms_id = gyms_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getTrainers_id() {
        return trainers_id;
    }

    public void setTrainers_id(Long trainers_id) {
        this.trainers_id = trainers_id;
    }

    public Long getGyms_id() {
        return gyms_id;
    }

    public void setGyms_id(Long gyms_id) {
        this.gyms_id = gyms_id;
    }
}
