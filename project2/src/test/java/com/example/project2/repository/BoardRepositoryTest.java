package com.example.project2.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // C
    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 300)
                .forEach(i -> {
                    Board board = Board.builder()
                            .content("content" + i)
                            .title("Title..." + i)
                            .writer("user" + i)
                            .build();
                    boardRepository.save(board);
                });

    }

    @Test
    public void selectOneTest() {
        System.out.println(boardRepository.findById(1L).get());
    }

    @Test
    public void selectAllTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void updateTest() {
        Board board = boardRepository.findById(6L).get();
        board.setContent("내용 변경");
        board.setTitle("제목 변경");
        board.setWriter("작가 변경");
        boardRepository.save(board);
    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(5L);
    }

    // 쿼리 메소드
    @Test
    public void testTitleList() {
        // boardRepository.findByTitle("Title...4").forEach(b -> System.out.println(b));
        // boardRepository.findByTitleLike("Title").forEach(b -> System.out.println(b));
        // boardRepository.findByTitleStartingWith("Title").forEach(b ->
        // System.out.println(b));
        // boardRepository.findByTitleEndingWith("1").forEach(b ->
        // System.out.println(b));
        // boardRepository.findByWriterContaining("user").forEach(b ->
        // System.out.println(b));
        boardRepository.findByWriterContainingOrTitleContaining("user", "Title").forEach(b -> System.out.println(b));
        // boardRepository.findByTitleContainingAndIdGreaterThan("Title", 41L).forEach(b
        // -> System.out.println(b));
        // boardRepository.findByIdGreaterThanOrderByIdDesc(0L).forEach(b ->
        // System.out.println(b));

        // 0(pageNumber) : 1 page 의미 , pageSize : 한 페이지에 보여질 게시물 개수
        // Pageable pageable = PageRequest.of(0, 10);
        // boardRepository.findByIdGreaterThanOrderByIdDesc(0L, pageable).forEach(b ->
        // System.out.println(b));

        // @Query 어노테이션
        // boardRepository.findByWriterList("user").forEach(i -> System.out.println(i));

    }

}
