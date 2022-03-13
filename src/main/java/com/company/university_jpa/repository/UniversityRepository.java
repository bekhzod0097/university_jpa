package com.company.university_jpa.repository;

import com.company.university_jpa.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Integer> {
}
