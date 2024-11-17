package com.shimady.manager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Сущность для ответа на запрос специальностей")
public class MajorResponse {
    @Schema(description = "Идентификатор специальности", example = "2L")
    private Long id;

    @Schema(description = "Название специальности", example = "Прикладная математика и информатика")
    private String name;

    @Schema(description = "Код специальности", example = "01.03.02")
    private String code;
}
