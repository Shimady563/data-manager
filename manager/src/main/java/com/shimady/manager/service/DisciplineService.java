package com.shimady.manager.service;

import com.shimady.manager.model.Discipline;
import com.shimady.manager.model.Major;
import com.shimady.manager.model.dto.DisciplinePayload;
import com.shimady.manager.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;
    private final MajorService majorService;
    private final ModelMapper mapper;

    @Transactional
    public void createDisciplineFromPayload(DisciplinePayload message, String majorName) {
        log.info("Creating discipline from payload with name: {}", message.getName());
        Discipline discipline = mapper.map(message, Discipline.class);
        Major major = majorService.getRawMajorByName(majorName);
        discipline.setMajor(major);
        disciplineRepository.save(discipline);
    }
}
