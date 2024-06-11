package com.example.course_java.service;
import com.example.course_java.domain.Memberships;
import com.example.course_java.repository.MembershipsRepository;
import com.example.course_java.repository.MembershipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipsService {
    private final MembershipsRepository membershipsRepository;
    @Autowired
    public MembershipsService(MembershipsRepository membershipsRepository) {
        this.membershipsRepository = membershipsRepository;
    }


    public List<Memberships> getAllMemberships() {
        return membershipsRepository.findAll();
    }

    public Optional<Memberships> getMembershipsById(Long membershipsId) {
        return membershipsRepository.findById(membershipsId);
    }

    public Memberships createMemberships(Memberships memberships) {
        return membershipsRepository.save(memberships);
    }

    public Memberships updateMemberships(Memberships memberships) {
        return membershipsRepository.save(memberships);
    }

    public void deleteMemberships(Long membershipsId) {
        membershipsRepository.deleteById(membershipsId);
    }
}
