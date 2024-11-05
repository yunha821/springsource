package com.example.mart.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.mart.entitiy.cascade.Child;
import com.example.mart.entitiy.cascade.Parent;
import com.example.mart.repository.cascade.ChildRepository;
import com.example.mart.repository.cascade.ParentRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ParentChildRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void testParentInsert() {

        Parent parent = Parent.builder().name("홍길동3").build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Child child = Child.builder().name("자식" + i).parent(parent).build();

            parent.getChilds().add(child);
        });
        parentRepository.save(parent);
    }

    @Test
    public void testChildRead() {
        // 자식 2 정보 조회(+ 부모)
        Child child = childRepository.findById(3L).get();
        System.out.println(child);
        System.out.println(child.getParent());

    }

    @Test
    public void testParentDelete() {

        // 부모 삭제 시 관련되어 있는 자식 같이 삭제
        // 자식삭제 코드를 작성하지 않고
        // Parent parent = parentRepository.findById(1L).get();
        // parent.getChilds().remove(0);
        // parent.getChilds().remove(1);
        // parent.getChilds().remove(2);

        // parentRepository.save(parent);

        parentRepository.deleteById(3L);

    }

    @Commit
    @Transactional
    @Test
    public void testParentDelete2() {

        // 부모 삭제 시 관련되어 있는 자식 같이 삭제
        // 자식삭제 코드를 작성하지 않고
        Parent parent = parentRepository.findById(1L).get();

        parent.getChilds().remove(0);
        // parent.getChilds().remove(1);
        // parent.getChilds().remove(2);

        parentRepository.save(parent);

    }

}
