package com.shimady.manager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Сущность для ответа на запрос студентов")
public class StudentResponse {
    @Schema(description = "Идентификатор студента", example = "1L")
    private Long id;

    @Schema(description = "ФИО студента", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(description = "Группа студента", example = "35/1")
    private String group;

    @Schema(description = "Курс студента", example = "3")
    private Integer course;

    @Schema(description = "Поле наличия или отсутствия зачетки", example = "true")
    private Boolean hasRecordBook;

    @Schema(description = "Назавние специальности студента", example = "Прикладная математика и информатика")
    private String majorName;
}
