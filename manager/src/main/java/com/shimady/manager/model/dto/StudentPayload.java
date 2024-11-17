package com.shimady.manager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentPayload {
    @NotEmpty
    @JsonProperty("full_name")
    private String fullName;

    @NotEmpty
    private String group;

    @NotNull
    private Integer course;
}
