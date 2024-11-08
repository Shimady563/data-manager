package com.shimady.manager.service;

import com.shimady.manager.exception.ResourceNotFoundException;
import com.shimady.manager.model.Major;
import com.shimady.manager.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    protected Major getMajorByName(String majorName) {
        return majorRepository.findByName(majorName)
                .orElseThrow(() -> new ResourceNotFoundException("Major with name: " + majorName + " not found"));

    }
}
