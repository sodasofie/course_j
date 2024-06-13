package com.example.course_java.controller;

import com.example.course_java.domain.Users;
import com.example.course_java.domain.Memberships;
import com.example.course_java.dto.MembershipsDTO;
import com.example.course_java.service.MembershipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/memberships")
public class MembershipsController {
    @Autowired
    private MembershipsService membershipsService;

    @GetMapping
    public List<MembershipsDTO> getAllMemberships() {
        List<Memberships> membershipss = membershipsService.getAllMemberships();
        return membershipss.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MembershipsDTO> getMembershipsById(@PathVariable Long id) {
        Optional<Memberships> memberships = membershipsService.getMembershipsById(id);
        return memberships.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MembershipsDTO createMemberships(@RequestBody MembershipsDTO membershipsDTO) {
        Memberships memberships = convertToEntity(membershipsDTO);
        Memberships createdMemberships = membershipsService.createMemberships(memberships);
        return convertToDTO(createdMemberships);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembershipsDTO> updateMemberships(@PathVariable Long id, @RequestBody MembershipsDTO membershipsDTO) {
        if (membershipsService.getMembershipsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        membershipsDTO.setId(id);
        Memberships memberships = convertToEntity(membershipsDTO);
        Memberships updatedMemberships = membershipsService.updateMemberships(memberships);
        return ResponseEntity.ok(convertToDTO(updatedMemberships));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberships(@PathVariable Long id) {
        if (membershipsService.getMembershipsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        membershipsService.deleteMemberships(id);
        return ResponseEntity.noContent().build();
    }

    private MembershipsDTO convertToDTO(Memberships memberships) {
        return new MembershipsDTO(
                memberships.getId(),
                memberships.getName(),
                memberships.getDescription(),
                memberships.getPrice(),
                memberships.getStart_date(),
                memberships.getEnd_date(),
                memberships.getUsers() != null ? memberships.getUsers().getId() : null
        );
    }

    private Memberships convertToEntity(MembershipsDTO membershipsDTO) {
        Memberships memberships = new Memberships();
        memberships.setId(membershipsDTO.getId());
        memberships.setName(membershipsDTO.getName());
        memberships.setDescription(membershipsDTO.getDescription());
        memberships.setPrice(membershipsDTO.getPrice());
        memberships.setStart_date(membershipsDTO.getStart_date());
        memberships.setEnd_date(membershipsDTO.getEnd_date());

        Users users = new Users();
        users.setId(membershipsDTO.getUsers_id());
        memberships.setUsers(users);

        return memberships;
    }
}
