package com.example.course_java.domain;

import javax.persistence.*;
@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "trainers_id", referencedColumnName = "id")
    private Trainers trainers;
    @ManyToOne
    @JoinColumn(name = "gyms_id", referencedColumnName = "id")
    private Gyms gyms;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Trainers getTrainers() {
        return trainers;
    }

    public void setTrainers(Trainers trainers) {
        this.trainers = trainers;
    }

    public Gyms getGyms() {
        return gyms;
    }

    public void setGyms(Gyms gyms) {
        this.gyms = gyms;
    }
}
