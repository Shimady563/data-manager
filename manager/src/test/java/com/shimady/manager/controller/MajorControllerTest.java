package com.shimady.manager.controller;

import com.shimady.manager.config.GenericTestConfig;
import com.shimady.manager.service.MajorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MajorController.class)
@Import(GenericTestConfig.class)
public class MajorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MajorService majorService;

    @Test
    public void shouldGetAllMajors() throws Exception{
        mockMvc.perform(get("/majors")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(majorService).should().getAllMajors();
    }
}
