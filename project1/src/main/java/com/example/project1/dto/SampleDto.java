package com.example.project1.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data

public class SampleDto {
    private Long id;
    private String first;
    private String last;
    private LocalDateTime regDateTime;

    public static Object builder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'builder'");
    }
}
