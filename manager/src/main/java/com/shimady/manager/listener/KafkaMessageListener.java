package com.shimady.manager.listener;

import com.shimady.manager.model.dto.DisciplinePayload;
import com.shimady.manager.model.dto.StudentPayload;
import com.shimady.manager.service.DisciplineService;
import com.shimady.manager.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final StudentService studentService;
    private final DisciplineService disciplineService;

    @KafkaListener(topics = "${kafka.topic.1}", groupId = "manager")
    public void listenStudent(
            @Payload StudentPayload message,
            @Header(KafkaHeaders.RECEIVED_KEY) String major,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition
    ) {
        log.info("Received student message: {}; with major: {}, from partition: {}",
                message,
                major,
                partition);
        studentService.createStudentFromPayload(message, major);
    }

    @KafkaListener(topics = "${kafka.topic.2}", groupId = "manager")
    public void listenDiscipline(
            @Payload DisciplinePayload message,
            @Header(KafkaHeaders.RECEIVED_KEY) String major,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition
    ) {
        log.info("Received discipline message: {}; with major: {}, from partition: {}",
                message,
                major,
                partition);
        disciplineService.createDisciplineFromPayload(message, major);
    }
}
