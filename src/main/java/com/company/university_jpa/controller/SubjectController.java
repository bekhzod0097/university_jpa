package com.company.university_jpa.controller;

import com.company.university_jpa.dto.UniversityDto;
import com.company.university_jpa.entity.Address;
import com.company.university_jpa.entity.Subject;
import com.company.university_jpa.entity.University;
import com.company.university_jpa.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @PostMapping("/create")
    public String add(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName){
            return "This subject is exists";
        }
        subjectRepository.save(subject);
        return "Subject Saved";
    }
    @GetMapping
    private List<Subject> getAll(){
        List<Subject> subjects = subjectRepository.findAll();
        return subjects;
    }
    @GetMapping("/{id}")
    private Subject getSubjectById(@PathVariable Integer id){
        Optional<Subject> optional = subjectRepository.findById(id);
        if (optional.isPresent()) {
            Subject subject = optional.get();
            return subject;
        }
        return null;
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Subject> optional = subjectRepository.findById(id);
        if (optional.isPresent()){
            Subject subject1 = optional.get();
            subject1.setName(subject.getName());

            return "Subject is updated!";
        }
        return "Subject is not exist";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        subjectRepository.deleteById(id);
        return "Subject is deleted!";
    }
}
