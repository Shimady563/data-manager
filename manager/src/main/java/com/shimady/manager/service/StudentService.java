package com.shimady.manager.service;

import com.shimady.manager.exception.ResourceNotFoundException;
import com.shimady.manager.model.Major;
import com.shimady.manager.model.Student;
import com.shimady.manager.model.dto.StudentPayload;
import com.shimady.manager.model.dto.StudentResponse;
import com.shimady.manager.model.dto.StudentUpdateRequest;
import com.shimady.manager.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final MajorService majorService;
    private final ModelMapper mapper;

    protected Student getRawStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id: " + id + " not found"));
    }

    @Transactional
    public void createStudentFromPayload(StudentPayload message, String majorName) {
        log.info("Creating student from payload with full name: {}", message.getFullName());
        Student student = mapper.map(message, Student.class);
        Major major = majorService.getRawMajorByName(majorName);
        student.setMajor(major);
        studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAllFetchMajor().stream()
                .map(s -> mapper.map(s, StudentResponse.class))
                .toList();
    }

    @Transactional
    public void updateStudent(Long id, StudentUpdateRequest request) {
        Student student = getRawStudentById(id);
        student.setFullName(request.getFullName());
        student.setCourse(request.getCourse());
        student.setGroup(request.getGroup());
        student.setHasRecordBook(request.getHasRecordBook());
        studentRepository.save(student);
    }
}
