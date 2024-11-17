package com.shimady.manager.repository;

import com.shimady.manager.config.DataJpaTesctontainersConfig;
import com.shimady.manager.model.Discipline;
import com.shimady.manager.model.Major;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(DataJpaTesctontainersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DisciplineRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DisciplineRepository disciplineRepository;

    private final Major major = new Major();

    @BeforeEach
    public void setUp() {
        major.setName("Major name");
        major.setCode("00.00.00");
        entityManager.persist(major);
        entityManager.flush();
    }

    @Test
    public void testSave() {
        var discipline = new Discipline();
        discipline.setName("name");
        discipline.setTerms(List.of(1, 2));
        discipline.setMajor(major);

        disciplineRepository.save(discipline);

        var disciplineOpt = disciplineRepository.findById(discipline.getId());

        assertTrue(disciplineOpt.isPresent());
        assertThat(disciplineOpt.get())
                .extracting(Discipline::getName, Discipline::getMajor, Discipline::getTerms)
                .contains(discipline.getName(), discipline.getMajor(), discipline.getTerms());
    }
}
