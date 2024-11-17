package com.shimady.manager.service;

import com.shimady.manager.model.Major;
import com.shimady.manager.model.Student;
import com.shimady.manager.model.dto.StudentPayload;
import com.shimady.manager.model.dto.StudentResponse;
import com.shimady.manager.model.dto.StudentUpdateRequest;
import com.shimady.manager.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MajorService majorService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void shouldCreateStudentFromPayload() {
        var payload = new StudentPayload();
        payload.setFullName("John Doe");
        var major = new Major();
        major.setName("Computer Science");
        var mappedStudent = new Student();
        mappedStudent.setFullName("John Doe");

        given(mapper.map(eq(payload), eq(Student.class))).willReturn(mappedStudent);
        given(majorService.getRawMajorByName(eq(major.getName()))).willReturn(major);

        studentService.createStudentFromPayload(payload, major.getName());

        then(studentRepository).should().save(eq(mappedStudent));
    }

    @Test
    public void shouldGetAllStudents() {
        var student1 = new Student();
        student1.setId(1L);
        student1.setFullName("John Doe");
        var student2 = new Student();
        student2.setId(2L);
        student2.setFullName("Jane Smith");
        var response1 = new StudentResponse();
        response1.setId(1L);
        response1.setFullName("John Doe");
        var response2 = new StudentResponse();
        response2.setId(2L);
        response2.setFullName("Jane Smith");

        given(studentRepository.findAllFetchMajor()).willReturn(List.of(student1, student2));
        given(mapper.map(eq(student1), eq(StudentResponse.class))).willReturn(response1);
        given(mapper.map(eq(student2), eq(StudentResponse.class))).willReturn(response2);

        studentService.getAllStudents();

        then(studentRepository).should().findAllFetchMajor();
    }

    @Test
    public void shouldUpdateStudent() {
        var id = 1L;
        var existingStudent = new Student();
        existingStudent.setId(id);
        existingStudent.setFullName("Old Name");
        var updateRequest = new StudentUpdateRequest();
        updateRequest.setFullName("New Name");
        updateRequest.setCourse(2);
        updateRequest.setGroup("A");
        updateRequest.setHasRecordBook(true);

        given(studentRepository.findById(id)).willReturn(Optional.of(existingStudent));

        studentService.updateStudent(id, updateRequest);

        then(studentRepository).should().save(eq(existingStudent));
    }
}
