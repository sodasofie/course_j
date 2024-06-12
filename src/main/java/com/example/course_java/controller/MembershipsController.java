package com.example.course_java.controller;

import com.example.course_java.domain.Memberships;
import com.example.course_java.service.MembershipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/memberships")
public class MembershipsController {
    @Autowired
    private MembershipsService membershipsService;

    @GetMapping
    public List<Memberships> getAllMemberships() {
        return membershipsService.getAllMemberships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Memberships> getMembershipsById(@PathVariable Long id) {
        Optional<Memberships> membership = membershipsService.getMembershipsById(id);
        return membership.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Memberships createMemberships(@RequestBody Memberships memberships) {
        return membershipsService.createMemberships(memberships);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Memberships> updateMemberships(@PathVariable Long id, @RequestBody Memberships memberships) {
        if (membershipsService.getMembershipsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        memberships.setId(id);
        return ResponseEntity.ok(membershipsService.updateMemberships(memberships));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberships(@PathVariable Long id) {
        if (membershipsService.getMembershipsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        membershipsService.deleteMemberships(id);
        return ResponseEntity.noContent().build();
    }
}
