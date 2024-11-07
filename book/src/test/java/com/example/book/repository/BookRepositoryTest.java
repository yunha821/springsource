package com.example.book.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void testCategoryList() {
        // 카테고리 목록 추출
        categoryRepository.findAll().forEach(i -> {
            System.out.println(i);
        });

        // 퍼플리셔 목록 추출
        publisherRepository.findAll().forEach(j -> {
            System.out.println(j);
        });
    }

    @Test
    public void testCategoryInsert() {
        categoryRepository.save(Category.builder().name("소설").build());
        categoryRepository.save(Category.builder().name("건강").build());
        categoryRepository.save(Category.builder().name("컴퓨터").build());
        categoryRepository.save(Category.builder().name("여행").build());
        categoryRepository.save(Category.builder().name("경제").build());

    }

    @Test
    public void testPublisherInsert() {
        publisherRepository.save(Publisher.builder().name("미래의창").build());
        publisherRepository.save(Publisher.builder().name("웅진리빙하우스").build());
        publisherRepository.save(Publisher.builder().name("길명사").build());
        publisherRepository.save(Publisher.builder().name("길벗").build());
        publisherRepository.save(Publisher.builder().name("문학과지성사").build());
    }

    @Test
    public void testBookInsert() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            // 무작위로 publisher, category 지정에 사용
            long num = (int) (Math.random() * 5) + 1;

            Book book = Book.builder()
                    .title("Book title" + i)
                    .writer("작가" + i)
                    .price(15000 + i)
                    .salePrice((int) (15000 * i * 0.9))
                    .category(Category.builder().id(num).build())
                    .publisher(Publisher.builder().id(num).build())
                    .build();
            bookRepository.save(book);
        });

    }

    @Transactional
    @Test
    public void testList() {
        // 도서 목록 전체 조회
        bookRepository.findAll().forEach(book -> {
            System.out.println(book);
            // category 정보
            System.out.println(book.getCategory());
            System.out.println(book.getPublisher());

        });
    }

    @Transactional
    @Test
    public void testGet() {
        // 특정 도서 조회
        Book book = bookRepository.findById(5L).get();
        System.out.println(book);
        System.out.println(book.getCategory().getName());
        System.out.println(book.getPublisher().getName());
    }

    @Test
    public void testUpdate() {
        // 특정 도서 수정
        Book book = bookRepository.findById(5L).get();
        book.setPrice(320000);
        book.setSalePrice(10000);
        bookRepository.save(book);
    }

    @Test
    public void testDelete() {
        bookRepository.deleteById(10L);
    }

}
