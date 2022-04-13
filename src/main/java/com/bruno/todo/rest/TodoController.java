package com.bruno.todo.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bruno.todo.model.Todo;
import com.bruno.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("http://localhost:4200")
public class TodoController {
	
	@Autowired
	private TodoRepository repository;
	
	@PostMapping
	public Todo save(@RequestBody Todo todo) {
		return repository.save(todo);
	}
	
	// exemplo: url/api/todos/1
	@GetMapping("{id}")
	public Todo getById(@PathVariable Long id) {
		return repository
						.findById(id)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	// exemplo: url/api/todos
	@GetMapping
	public List<Todo> getAll(){
		return repository.findAll();
	}
	
	// exemplo: url/api/todos/1
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	// vai retornar um Todo, pois vai ser o Todo com status atualizado
	@PatchMapping("{id}/done")
	public Todo markAsDone(@PathVariable Long id) {
		return repository.findById(id)
							.map(todoEncontrado -> {
								todoEncontrado.setDone(true);
								todoEncontrado.setDoneDate(LocalDateTime.now());
								repository.save(todoEncontrado);
								return todoEncontrado;
							}).orElse(null);
	}
	
}



// se nao encontrar o id, ele vai retornar o erro do ResponseStatusException de NOT FOUND