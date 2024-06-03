package com.example.course_java.service;
import com.example.course_java.domain.Classes;
import com.example.course_java.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClassesService {
    @Autowired
    private ClassesRepository classesRepository;

    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    public Optional<Classes> getClassesById(Long classesId) {
        return classesRepository.findById(classesId);
    }

    public Classes createClasses(Classes classes) {
        return classesRepository.save(classes);
    }

    public Classes updateClasses(Classes classes) {
        return classesRepository.save(classes);
    }

    public void deleteClasses(Long classesId) {
        classesRepository.deleteById(classesId);
    }
}
