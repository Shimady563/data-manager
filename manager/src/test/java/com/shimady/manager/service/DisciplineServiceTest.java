package com.shimady.manager.service;

import com.shimady.manager.model.Discipline;
import com.shimady.manager.model.Major;
import com.shimady.manager.model.dto.DisciplinePayload;
import com.shimady.manager.repository.DisciplineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class DisciplineServiceTest {
    @Mock
    private DisciplineRepository disciplineRepository;

    @Mock
    private MajorService majorService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private DisciplineService disciplineService;

    @Test
    public void shouldCreateDisciplineFromPayload() {
        var majorName = "Major Name";
        var payload = new DisciplinePayload();
        payload.setName("Name");
        payload.setTerms(List.of(1, 2));
        var major = new Major();
        major.setName(majorName);
        var discipline = new Discipline();
        discipline.setName(payload.getName());
        discipline.setTerms(payload.getTerms());
        discipline.setMajor(major);

        given(majorService.getRawMajorByName(eq(majorName))).willReturn(major);
        given(mapper.map(eq(payload), eq(Discipline.class))).willReturn(discipline);

        disciplineService.createDisciplineFromPayload(payload, majorName);

        then(disciplineRepository).should().save(eq(discipline));
    }

}
