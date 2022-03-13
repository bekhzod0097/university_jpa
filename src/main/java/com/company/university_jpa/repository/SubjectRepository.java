package com.company.university_jpa.repository;

import com.company.university_jpa.entity.Address;
import com.company.university_jpa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean existsByName(String name);
}
