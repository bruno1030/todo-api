package com.bruno.todo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private Boolean done;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdDate;    // data e hora que a tarefa foi criada
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime doneDate;    // data e hora que a tarefa foi finalizada
	
	@PrePersist    //anotacao de metodo que deverá ser executado antes da persistencia dos dados, pra data nao fica null em tempo de execucao
	public void beforeSave() {
		setCreatedDate(LocalDateTime.now());
	}
	
}



// esse projeto se refere a um controle de tarefas que devem ser executadas (como um "to do list")
// essa entidade "Todo" vai representar uma tabela no meu banco de dados
// ela é portanto o ponteiro a ser seguido na criação do meu banco