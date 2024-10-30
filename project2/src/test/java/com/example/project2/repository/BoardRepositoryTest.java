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

        IntStream.rangeClosed(1, 20)
                .forEach(i -> {
                    Board board = Board.builder()
                            .content("게시판" + i)
                            .title("제목" + i)
                            .writer("작가" + i)
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

}
