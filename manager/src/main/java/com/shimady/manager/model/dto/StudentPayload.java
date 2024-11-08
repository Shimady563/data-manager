package com.shimady.manager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentPayload {
    @JsonProperty("full_name")
    private String fullName;
    private String group;
    private Integer course;
}
