package com.example.mart.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entitiy.item.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
