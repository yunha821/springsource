package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDto {

    private Long id;

    private Boolean completed;

    private Boolean important;

    private String title;

}
