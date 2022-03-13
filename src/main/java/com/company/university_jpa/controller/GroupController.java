package com.company.university_jpa.controller;

import com.company.university_jpa.dto.GroupDto;
import com.company.university_jpa.entity.Faculty;
import com.company.university_jpa.entity.Group;
import com.company.university_jpa.entity.Subject;
import com.company.university_jpa.repository.FacultyRepository;
import com.company.university_jpa.repository.GroupRepository;
import com.company.university_jpa.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @PostMapping("/create")
    public String add(@RequestBody GroupDto groupDtp){
        Group group = new Group();
        group.setName(groupDtp.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDtp.getFacultyId());
        if (!optionalFaculty.isPresent())
            return "Such faculty is not found";

        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "group saved";
    }
    //VAZIRLIK UCHUN
    @GetMapping
    private List<Group> getAll(){
        List<Group> groups = groupRepository.findAll();
        return groups;
    }
    @GetMapping("/{id}")
    private Group getGroupById(@PathVariable Integer id){
        Optional<Group> optional = groupRepository.findById(id);
        if (optional.isPresent()) {
            Group group = optional.get();
            return group;
        }
        return null;
    }
    // Univer xodimi uchun
    @GetMapping("/byUniversityId/{id}")
    private List<Group> getGroupByUniversityId(@PathVariable Integer id){
        List<Group> allByFaculty_universityId = groupRepository.findAllByFaculty_UniversityId(id);
//        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(id);
        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(id);

        return allByFaculty_universityId;
//        return null;
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody GroupDto groupDto){
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (!groupOptional.isPresent()) {
            return "Group is not exist";
        }
        Group group = groupOptional.get();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return "Faculty is not exist";
        }

        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);

        return "updated";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        groupRepository.deleteById(id);
        return "Group is deleted!";
    }
}
