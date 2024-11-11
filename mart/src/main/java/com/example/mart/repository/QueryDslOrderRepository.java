package com.example.mart.repository;

import java.util.List;

import com.example.mart.entitiy.item.Item;
import com.example.mart.entitiy.item.Member;

public interface QueryDslOrderRepository {

    List<Member> members();

    List<Item> items();

    List<Object[]> joinTest();

}
