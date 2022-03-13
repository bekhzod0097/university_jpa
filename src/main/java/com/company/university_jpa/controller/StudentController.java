package com.company.university_jpa.controller;

import com.company.university_jpa.entity.Student;
import com.company.university_jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    // vazirlik
    // univer
    // faculty
    // guruh rahbari

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/create")
    public String add(@RequestBody Student student){
//        boolean existsByName = studentRepository.existsByName(student.getName());
//        if (existsByName){
//            return "This student is exists";
//        }
//        studentRepository.save(student);
        return "Student Saved";
    }
    //for vazirlik
    @GetMapping("/forMinistry")
    private Page<Student> getAllForMinistry(@RequestParam int page){
        //select * from student limit 10 offset 0
        //select * from student limit 10 offset 10
        Pageable pageable = PageRequest.of(page, 10);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentPage;
    }
    //for vazirlik
    @GetMapping("/forUniversity/{id}")
    private Page<Student> getAllForUniversity(@PathVariable Integer univestityId,
                                              @RequestParam int page){

        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(univestityId, pageable);

        return studentPage;
    }
    @GetMapping("/{id}")
    private Student getStudentById(@PathVariable Integer id){
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student student = optional.get();
            return student;
        }
        return null;
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody Student student){
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()){
            Student student1 = optional.get();


            return "Student is updated!";
        }
        return "Student is not exist";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "Student is deleted!";
    }
}
