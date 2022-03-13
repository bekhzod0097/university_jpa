package com.company.university_jpa.controller;


import com.company.university_jpa.dto.FacultyDto;
import com.company.university_jpa.entity.Faculty;
import com.company.university_jpa.entity.Subject;
import com.company.university_jpa.entity.University;
import com.company.university_jpa.repository.FacultyRepository;
import com.company.university_jpa.repository.SubjectRepository;
import com.company.university_jpa.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @PostMapping("/create")
    public String add(@RequestBody FacultyDto facultyDto){
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if(exists)
            return "This University has such faculty";


        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optional = universityRepository.findById(facultyDto.getUniversityId());
        if (!optional.isPresent()) {
            return "Univer not found";
        }
        faculty.setUniversity(optional.get());
        facultyRepository.save(faculty);
        return "Faculty Saved";
    }

    //VAZIRLIK UCHUN
    @GetMapping
    private List<Faculty> getAll(){
        List<Faculty> facultys = facultyRepository.findAll();
        return facultys;
    }

    @GetMapping("/{id}")
    private Faculty getFacultyById(@PathVariable Integer id){
        Optional<Faculty> optional = facultyRepository.findById(id);
        if (optional.isPresent()) {
            Faculty faculty = optional.get();
            return faculty;
        }
        return null;
    }

    //UNIVERSITET UCHUN
    @GetMapping("/byUniversityId/{id}")
    private List<Faculty> getFacultyByUniversityId(@PathVariable Integer id){
        List<Faculty> facultyList = facultyRepository.findAllByUniversityId(id);
        return facultyList;
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Optional<Faculty> optional = facultyRepository.findById(id);
        if (optional.isPresent()){
            Faculty faculty1 = optional.get();
            faculty1.setName(facultyDto.getName());

            University university = universityRepository.findById(facultyDto.getUniversityId())
                    .orElseThrow(() -> new RuntimeException("University not found"));

            faculty1.setUniversity(university);
            facultyRepository.save(faculty1);
            return "Faculty is updated!";
        }
        return "Faculty is not exist";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        try {
            facultyRepository.deleteById(id);
            return "Faculty is deleted!";

        } catch (Exception e){
            e.printStackTrace();
            return "error in deleting";
        }
    }
}
