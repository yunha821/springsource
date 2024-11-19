package com.example.board.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

// Page<Book> result 결과를 담는 Dto
// Entity ==> Dto : result.getContent() ==> List<BookDto> 변경

@Data
public class PageResultDto<DTO, EN> {

    // 화면에 보여줄 목록
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 목록 사이즈
    private int size;

    // 시작 페이지, 끝 페이지 번호
    private int start, end;

    // 이전, 다음 여부
    private boolean prev, next;

    // 화면에 보여줄 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {

        // List<Book> books = result.getContent();
        // List<BookDto> list = books.stream().map(b ->
        // entityToDto(b)).collect(Collectors.toList());

        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        // 사용자가 요청한 페이지 번호
        // 0 이 1 page
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

        this.start = tempEnd - 9;
        this.prev = this.start > 1;
        this.end = totalPage > tempEnd ? tempEnd : totalPage;
        this.next = totalPage > tempEnd;

        // IntStream.rangeClosed(start, end) : int

        pageList = IntStream.rangeClosed(start, end)
                .boxed() // int ==> Integer
                .collect(Collectors.toList());
    }
}
