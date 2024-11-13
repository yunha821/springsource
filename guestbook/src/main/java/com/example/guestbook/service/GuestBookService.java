package com.example.guestbook.service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;

public interface GuestBookService {

    // 등록
    Long register(GuestBookDto dto);

    // 조회
    GuestBookDto read(Long gno);

    // 전체 조회
    PageResultDto<GuestBookDto, GuestBook> list(PageRequestDto requestDto);

    // 수정
    Long update(GuestBookDto dto);

    // 삭제
    void delete(Long gno);

    // dtoToEntity
    default GuestBook dtoToEntity(GuestBookDto dto) {
        return GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
    }

    // entityToDto
    public default GuestBookDto entityToDto(GuestBook entity) {
        return GuestBookDto.builder()
                .gno(entity.getGno())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .title(entity.getTitle())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }
}
