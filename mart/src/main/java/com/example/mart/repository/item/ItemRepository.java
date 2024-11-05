package com.example.mart.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entitiy.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
