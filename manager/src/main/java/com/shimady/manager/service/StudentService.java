package com.shimady.manager.service;

import com.shimady.manager.model.Major;
import com.shimady.manager.model.Student;
import com.shimady.manager.model.dto.StudentPayload;
import com.shimady.manager.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final MajorService majorService;
    private final ModelMapper mapper;

    @Transactional
    public void createFromPayload(StudentPayload message, String majorName) {
        log.info("Creating student from payload with full name: {}", message.getFullName());
        Student student = mapper.map(message, Student.class);
        Major major = majorService.getMajorByName(majorName);
        student.setMajor(major);
        studentRepository.save(student);
    }
}
