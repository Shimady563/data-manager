package com.shimady.manager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Сущность для запроса на обновление студента")
public class StudentUpdateRequest {
    @NotEmpty(message = "Full name cannot be empty")
    @Schema(description = "ФИО студента", example = "Иванов Иван Иванович")
    private String fullName;

    @NotNull(message = "Course cannot be empty")
    @Schema(description = "Курс студента", example = "3")
    private Integer course;

    @NotEmpty(message = "Group cannot be empty")
    @Schema(description = "Группа студента", example = "35/1")
    private String group;

    @NotNull(message = "Record book field cannot be empty")
    @Schema(description = "Поле наличия или отсутствия зачетки", example = "true")
    private Boolean hasRecordBook;
}
