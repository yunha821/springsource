package com.example.guestbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
@Getter
public class GuestBookDto {

    private Long gno;

    @NotBlank(message = "Writer는 필수 요소 입니다")
    private String writer;

    @NotEmpty(message = "Title은 필수 요소 입니다")
    private String title;

    @NotBlank(message = "Content은 필수 요소 입니다")
    private String content;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
