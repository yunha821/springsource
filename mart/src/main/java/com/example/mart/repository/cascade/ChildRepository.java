package com.example.mart.repository.cascade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entitiy.cascade.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {

}
