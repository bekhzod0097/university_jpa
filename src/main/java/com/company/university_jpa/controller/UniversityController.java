package com.company.university_jpa.controller;

import com.company.university_jpa.dto.UniversityDto;
import com.company.university_jpa.entity.Address;
import com.company.university_jpa.entity.University;
import com.company.university_jpa.repository.AddressRepository;
import com.company.university_jpa.repository.UniversityRepository;
import com.company.university_jpa.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private AddressRepository addressRepository;


    @GetMapping
    private List<University> getUniversities(){
        List<University> universities = universityRepository.findAll();
        return universities;
    }
    @GetMapping("/{id}")
    private University getUniversityById(@PathVariable Integer id){
        Optional<University> optional = universityRepository.findById(id);
        if (optional.isPresent()) {
            University university = optional.get();
            return university;
        }
        return null;
    }
    @PostMapping("/create")
    private String addUniversity(@RequestBody UniversityDto universityDto){

        University university = new University();
        university.setName(universityDto.getName());

        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        addressRepository.save(address);

        university.setAddress(address);

        universityRepository.save(university);
        return "University added";
    }

    @PutMapping("/update/{id}")
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        Optional<University> optional = universityRepository.findById(id);
        if (optional.isPresent()){
            University university = optional.get();
            university.setName(universityDto.getName());

            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

            university.setAddress(address);
            universityRepository.save(university);

            return "University is updated!";
        }
        return "University is not exist";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "University is deleted!";
    }
}
