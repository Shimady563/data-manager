package com.shimady.manager.controller;

import com.shimady.manager.model.dto.MajorResponse;
import com.shimady.manager.service.MajorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/majors")
@RequiredArgsConstructor
@Tag(name = "Major controller")
public class MajorController {
    private final MajorService majorService;

    @GetMapping("")
    @Operation(summary = "Получение всех специальностей")
    public List<MajorResponse> getAllMajors() {
        return majorService.getAllMajors();
    }
}
