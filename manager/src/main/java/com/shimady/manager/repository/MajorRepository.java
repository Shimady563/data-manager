package com.shimady.manager.repository;

import com.shimady.manager.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Optional<Major> findByName(String name);
}
