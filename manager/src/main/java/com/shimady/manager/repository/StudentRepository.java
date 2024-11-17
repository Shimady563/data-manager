package com.shimady.manager.repository;

import com.shimady.manager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s left join fetch s.major")
    List<Student> findAllFetchMajor();
}
