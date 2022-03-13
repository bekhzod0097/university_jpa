package com.company.university_jpa.repository;

import com.company.university_jpa.entity.Address;
import com.company.university_jpa.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    boolean existsByNameAndUniversityId(String name, Integer id);

    List<Faculty> findAllByUniversityId(Integer id);
}
