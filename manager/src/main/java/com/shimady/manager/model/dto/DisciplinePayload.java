package com.shimady.manager.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DisciplinePayload {
    private String name;
    private List<Integer> terms;
}
