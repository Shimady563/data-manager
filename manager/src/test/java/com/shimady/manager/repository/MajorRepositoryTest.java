package com.shimady.manager.repository;

import com.shimady.manager.config.DataJpaTesctontainersConfig;
import com.shimady.manager.model.Major;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(DataJpaTesctontainersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MajorRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MajorRepository majorRepository;

    private final Major major = new Major();

    @BeforeEach
    public void setUp() {
        major.setName("Major name");
        major.setCode("00.00.00");
        entityManager.persist(major);
        entityManager.flush();
    }

    @Test
    public void testFindByName() {
        var majorOpt = majorRepository.findByName(major.getName());

        assertTrue(majorOpt.isPresent());
        assertEquals(major.getName(), majorOpt.get().getName());
    }

    @Test
    public void testFindAll() {
        var majors = majorRepository.findAll();

        assertThat(majors).hasSize(5).contains(major);
    }
}
