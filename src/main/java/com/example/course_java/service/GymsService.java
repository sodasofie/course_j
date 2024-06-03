package com.example.course_java.service;
import com.example.course_java.domain.Gyms;
import com.example.course_java.repository.GymsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GymsService {
    @Autowired
    private GymsRepository gymsRepository;

    public List<Gyms> getAllGyms() {
        return gymsRepository.findAll();
    }

    public Optional<Gyms> getGymsById(Long gymsId) {
        return gymsRepository.findById(gymsId);
    }

    public Gyms createGyms(Gyms gyms) {
        return gymsRepository.save(gyms);
    }

    public Gyms updateGyms(Gyms gyms) {
        return gymsRepository.save(gyms);
    }

    public void deleteGyms(Long gymsId) {
        gymsRepository.deleteById(gymsId);
    }
}
