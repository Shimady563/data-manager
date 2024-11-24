package com.shimady.manager.service;

import com.shimady.manager.exception.ResourceNotFoundException;
import com.shimady.manager.model.Major;
import com.shimady.manager.model.dto.MajorResponse;
import com.shimady.manager.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;
    private final ModelMapper mapper;

    protected Major getRawMajorByName(String majorName) {
        log.info("Getting major by name: {}", majorName);
        return majorRepository.findByName(majorName)
                .orElseThrow(() -> new ResourceNotFoundException("Major with name: " + majorName + " not found"));

    }

    @Transactional(readOnly = true)
    public List<MajorResponse> getAllMajors() {
        log.info("Getting all majors");
        return majorRepository.findAll().stream()
                .map(m -> mapper.map(m, MajorResponse.class))
                .toList();

    }
}
