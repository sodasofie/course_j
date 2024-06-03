package com.example.course_java.controller;

import com.example.course_java.domain.Memberships;
import com.example.course_java.repository.MembershipsRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Memberships getMembershipsById(@PathVariable Long id) {
        return membershipsRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Memberships createMemberships(@RequestBody Memberships memberships) {
        return membershipsRepository.save(memberships);
    }

    @PutMapping("/{id}")
    public Memberships updateMemberships(@PathVariable Long id, @RequestBody Memberships memberships) {
        memberships.setId(id);
        return membershipsRepository.save(memberships);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberships(@PathVariable Long id) {
        membershipsRepository.deleteById(id);
    }
}
