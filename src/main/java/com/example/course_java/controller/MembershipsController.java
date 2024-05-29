package com.example.course_java.controller;

import com.example.course_java.domain.Memberships;
import com.example.course_java.repository.MembershipsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipsController {
    private final MembershipsRepository membershipsRepository;

    public MembershipsController(MembershipsRepository membershipsRepository) {
        this.membershipsRepository = membershipsRepository;
    }

    @GetMapping("/")
    public List<Memberships> getAllMemberships() {
        return membershipsRepository.findAll();
    }
}
