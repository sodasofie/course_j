package com.example.course_java.controller;

import com.example.course_java.domain.Classes;
import com.example.course_java.domain.Memberships;
import com.example.course_java.domain.Schedule;
import com.example.course_java.dto.ScheduleDTO;
import com.example.course_java.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public List<ScheduleDTO> getAllSchedule() {
        List<Schedule> schedules = scheduleService.getAllSchedule();
        return schedules.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleService.getScheduleById(id);
        return schedule.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertToEntity(scheduleDTO);
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return convertToDTO(createdSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        if (scheduleService.getScheduleById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        scheduleDTO.setId(id);
        Schedule schedule = convertToEntity(scheduleDTO);
        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok(convertToDTO(updatedSchedule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        if (scheduleService.getScheduleById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getStart_datetime(),
                schedule.getEnd_datetime(),
                schedule.getClasses().getId(),
                schedule.getMemberships() != null ? schedule.getMemberships().getId() : null
        );
    }

    private Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setStart_datetime(scheduleDTO.getStart_datetime());
        schedule.setEnd_datetime(scheduleDTO.getEnd_datetime());

        Classes classes = new Classes();
        classes.setId(scheduleDTO.getClassesId());
        schedule.setClasses(classes);


        Memberships memberships = new Memberships();
        memberships.setId(scheduleDTO.getMemberships_id());
        schedule.setMemberships(memberships);

        return schedule;
    }
}
