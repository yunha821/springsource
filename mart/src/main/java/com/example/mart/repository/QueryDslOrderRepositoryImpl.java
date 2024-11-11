package com.example.mart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.mart.entitiy.item.Item;
import com.example.mart.entitiy.item.Member;
import com.example.mart.entitiy.item.QMember;
import com.querydsl.jpa.JPQLQuery;

import jakarta.persistence.criteria.Order;

public class QueryDslOrderRepositoryImpl extends QuerydslRepositorySupport implements QueryDslOrderRepository {

    public QueryDslOrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Member> members() {
        // select $ from member where name='user1' order by name desc
        QMember qMember = QMember.member;
        JPQLQuery<Member> query = from(qMember);
        query.where(qMember.name.eq("user1")).orderBy(qMember.name.desc());
        JPQLQuery<Member> tuple = query.select(qMember);
        System.out.println(tuple);
        return tuple.fetch();
    }

    @Override
    public List<Item> items() {
        return null;

    }

    @Override
    public List<Object[]> joinTest() {
        return null;
    }
}
