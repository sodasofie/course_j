package com.example.course_java.dto;

import java.time.LocalDateTime;

public class ScheduleDTO {
    private Long id;
    private LocalDateTime start_datetime;
    private LocalDateTime end_datetime;
    private Long classes_id;
    private Long memberships_id;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Long id, LocalDateTime start_datetime, LocalDateTime end_datetime, Long classes_id, Long memberships_id) {
        this.id = id;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
        this.classes_id = classes_id;
        this.memberships_id = memberships_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(LocalDateTime start_datetime) {
        this.start_datetime = start_datetime;
    }

    public LocalDateTime getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(LocalDateTime end_datetime) {
        this.end_datetime = end_datetime;
    }

    public Long getClassesId() {
        return classes_id;
    }

    public void setClassesId(Long classes_id) {
        this.classes_id = classes_id;
    }

    public Long getMemberships_id() {
        return memberships_id;
    }

    public void setMemberships_id(Long memberships_id) {
        this.memberships_id = memberships_id;
    }
}
