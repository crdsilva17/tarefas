package com.rocha.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocha.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
