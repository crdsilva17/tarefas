package com.rocha.tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TarefaRequest(
    @NotBlank (message = "O título da tarefa não pode estar em branco")
    @Size (max = 50, message = "O título da tarefa não pode ter mais de 50 caracteres")
    String titulo, 
    @NotBlank (message = "A descrição da tarefa não pode estar em branco")
    String descricao, 
    @NotNull (message = "O status de conclusão da tarefa dever ser informado")
    boolean concluida) {}
