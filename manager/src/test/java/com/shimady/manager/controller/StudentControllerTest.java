package com.shimady.manager.controller;

import com.shimady.manager.config.GenericTestConfig;
import com.shimady.manager.model.dto.StudentUpdateRequest;
import com.shimady.manager.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@Import(GenericTestConfig.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldGetAllStudents() throws Exception {
        mockMvc.perform(get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(studentService).should().getAllStudents();
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        var id = 1L;
        var request = new StudentUpdateRequest();
        request.setFullName("Full Name");
        request.setCourse(1);
        request.setGroup("12/1");
        request.setHasRecordBook(false);

        mockMvc.perform(put("/students/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        then(studentService).should().updateStudent(eq(id), eq(request));
    }
}
