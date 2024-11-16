package com.shimady.manager.listener;

import com.shimady.manager.config.KafkaTestConfig;
import com.shimady.manager.config.TestcontainersConfig;
import com.shimady.manager.model.dto.DisciplinePayload;
import com.shimady.manager.model.dto.StudentPayload;
import com.shimady.manager.service.DisciplineService;
import com.shimady.manager.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.ContainerTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.after;

@SpringBootTest
@Import({TestcontainersConfig.class, KafkaTestConfig.class})
public class KafkaMessageListenerIntegrationTest {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @SpyBean
    private StudentService studentService;

    @SpyBean
    private DisciplineService disciplineService;

    @BeforeEach
    public void setUp() {
        // Wait for the listener container to be assigned a partition
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(container ->
                ContainerTestUtils.waitForAssignment(container, 1));
    }

    @Test
    void shouldProcessStudentMessage() {
        var key = "Прикладная информатика";
        var payload = new StudentPayload();
        payload.setFullName("Name");
        payload.setCourse(1);
        payload.setGroup("group");

        kafkaTemplate.send("student", key, payload);

        then(studentService).should(after(2000)).createStudentFromPayload(eq(payload), eq(key));
    }

    @Test
    void shouldProcessDisciplineMessage() {
        var payload = new DisciplinePayload();
        payload.setName("Name");
        payload.setTerms(List.of(1));
        var key = "Прикладная информатика";

        kafkaTemplate.send("discipline", key, payload);

        then(disciplineService).should(after(2000)).createDisciplineFromPayload(eq(payload), eq(key));
    }
}
