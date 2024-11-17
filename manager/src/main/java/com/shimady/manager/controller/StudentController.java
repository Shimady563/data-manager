package com.shimady.manager.controller;

import com.shimady.manager.model.dto.StudentResponse;
import com.shimady.manager.model.dto.StudentUpdateRequest;
import com.shimady.manager.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "Student controller")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("")
    @Operation(summary = "Получение всех студентов")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных студентов")
    public void updateStudent(
            @PathVariable @Parameter(description = "Идентификатор пользователей", example = "1L") Long id,
            @Valid @RequestBody StudentUpdateRequest request
    ) {
        studentService.updateStudent(id, request);
    }
}
