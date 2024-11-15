package com.shimady.manager.repository;

import com.shimady.manager.config.DataJpaTesctontainersConfig;
import com.shimady.manager.model.Major;
import com.shimady.manager.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(DataJpaTesctontainersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    private final Student student = new Student();
    private final Major major = new Major();

    @BeforeEach
    public void setUp() {
        major.setName("Major name");
        major.setCode("00.00.00");
        entityManager.persist(major);

        student.setCourse(1);
        student.setGroup("11/1");
        student.setFullName("Full name");
        student.setHasRecordBook(true);
        student.setMajor(major);
        entityManager.persist(student);
        entityManager.flush();
    }

    @Test
    public void testFindById() {
        var studentOpt = studentRepository.findById(student.getId());

        assertTrue(studentOpt.isPresent());
        assertEquals(student.getId(), studentOpt.get().getId());
    }

    @Test
    public void testFindAllFetchMajor() {
        var students = studentRepository.findAll();

        assertThat(students).hasSize(2)
                .extracting(Student::getMajor)
                .contains(major);
    }

    @Test
    public void testSave() {
        var student = new Student();
        student.setCourse(2);
        student.setGroup("21/1");
        student.setFullName("Name");
        student.setHasRecordBook(true);
        student.setMajor(major);

        studentRepository.save(student);

        var studentOpt = studentRepository.findById(student.getId());

        assertTrue(studentOpt.isPresent());
        assertThat(studentOpt.get())
                .extracting(Student::getMajor, Student::getCourse, Student::getGroup, Student::getFullName, Student::getHasRecordBook)
                .contains(student.getMajor(), student.getCourse(), student.getGroup(), student.getFullName(), student.getHasRecordBook());
    }
}
