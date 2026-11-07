package com.rocha.tarefas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rocha.tarefas.domain.Tarefa;
import com.rocha.tarefas.dto.TarefaRequest;
import com.rocha.tarefas.dto.TarefaResponse;

@Mapper(componentModel = "spring")
public class TarefaMapper {
    
    // Realiza o mapeamento de Tarefa para TarefaResponse
    public TarefaResponse toResponse(Tarefa tarefa) {
        if (tarefa == null) {
            return null;
        }
        return new TarefaResponse(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.isConcluida());
    }

    // Realiza o mapeamento de TarefaRequest para Tarefa
    public Tarefa toEntity(TarefaRequest tarefaRequest) {
        if (tarefaRequest == null) {
            return null;
        }
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaRequest.titulo());
        tarefa.setDescricao(tarefaRequest.descricao());
        tarefa.setConcluida(tarefaRequest.concluida());
        return tarefa;
    }
}
