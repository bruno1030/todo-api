package com.bruno.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
