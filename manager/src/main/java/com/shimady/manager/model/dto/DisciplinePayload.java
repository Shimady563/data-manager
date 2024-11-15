package com.shimady.manager.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DisciplinePayload {
    @NotEmpty
    private String name;

    @NotNull
    private List<Integer> terms;
}
