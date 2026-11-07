package com.rocha.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocha.tarefas.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
