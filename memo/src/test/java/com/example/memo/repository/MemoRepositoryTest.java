package com.example.memo.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testMemoInsert() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("memo" + i)
                    .build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testMemoRead() {
        // 특정 메모(5번) 가져오기
        Memo memo = memoRepository.findById(25L).get();
        System.out.println(memo);

        // 전체 메모 가져오기
        List<Memo> list = memoRepository.findAll();
        list.forEach(i -> System.out.println(i));

    }

    @Test
    public void testMemoUpdate() {
        Memo memo = memoRepository.findById(27L).get();
        memo.setMemoText("수정한 memo");
        memoRepository.save(memo);
    }

    @Test
    public void testMemoDelete() {
        memoRepository.deleteById(29L);
    }

}
