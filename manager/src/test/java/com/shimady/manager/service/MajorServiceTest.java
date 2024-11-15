package com.shimady.manager.service;

import com.shimady.manager.model.Major;
import com.shimady.manager.model.dto.MajorResponse;
import com.shimady.manager.repository.MajorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MajorServiceTest {
    @Mock
    private MajorRepository majorRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private MajorService majorService;

    @Test
    public void shouldGetAllMajors() {
        var major1 = new Major();
        major1.setId(1L);
        major1.setName("name1");
        var major2 = new Major();
        major2.setId(1L);
        major2.setName("name2");
        var response1 = new MajorResponse();
        response1.setId(major1.getId());
        response1.setName(major1.getName());
        var response2 = new MajorResponse();
        response2.setId(major2.getId());
        response2.setName(major2.getName());

        given(majorRepository.findAll()).willReturn(List.of(major1, major2));
        given(mapper.map(eq(major1), eq(MajorResponse.class))).willReturn(response1);
        given(mapper.map(eq(major2), eq(MajorResponse.class))).willReturn(response2);

        majorService.getAllMajors();

        then(majorRepository).should().findAll();
    }
}
