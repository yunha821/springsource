package com.example.project1.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Builder;

@Data
@Builder

public class SampleDto {
    private Long id;
    private String first;
    private String last;
    private LocalDateTime regDateTime;

}
