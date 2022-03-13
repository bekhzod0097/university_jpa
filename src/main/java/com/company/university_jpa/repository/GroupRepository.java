package com.company.university_jpa.repository;

import com.company.university_jpa.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByFaculty_UniversityId(Integer faculty_university_id) ;

//    @Query("select gr from groups gr where gr.faculty.university.id =: universityId")
//    List<Group> getGroupsByUniversityId(Integer universityId);

    @Query(value = "select all\n" +
            "from groups g\n" +
            "         join faculty f on f.id = g.faculty_id\n" +
            "         join university u on u.id = f.university_id " +
            "where u.id=:universityId",nativeQuery = true)
    List<Group> getGroupsByUniversityIdNative(Integer universityId);

}
