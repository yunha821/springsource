package com.example.movie.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    // 200개

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 영화번호 임의 추출
            Long mno = (long) (Math.random() * 50) + 1;
            Movie movie = Movie.builder().mno(mno).build();

            // 리뷰 멤버 임의 추출
            Long mid = (long) (Math.random() * 50) + 1;
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .grade((int) (Math.random() * 5) + 1)
                    .text("이 영화에 대한 감상평은.." + i)
                    .movie(movie)
                    .member(member)
                    .build();
            reviewRepository.save(review);

        });
    }
}
