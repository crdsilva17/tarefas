package com.rocha.tarefas.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.tarefas.domain.Tarefa;
import com.rocha.tarefas.dto.TarefaRequest;
import com.rocha.tarefas.dto.TarefaResponse;
import com.rocha.tarefas.mapper.TarefaMapper;
import com.rocha.tarefas.services.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TarefaController {
    private final TarefaService tarefaService;
    private final TarefaMapper tarefaMapper;

    public TarefaController(TarefaService tarefaService, TarefaMapper tarefaMapper) {
        this.tarefaService = tarefaService;
        this.tarefaMapper = tarefaMapper;
    }

    @GetMapping
    @Operation(summary = "Busca Lista de Tarefas", description = "Retorna a lista de todas as tarefas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefas localizadas com sucesso")
    }) 
    public ResponseEntity<List<TarefaResponse>> getTarefaService() {
        return ResponseEntity.ok(tarefaService.listarTarefas()
                                .stream().map(tarefaMapper::toResponse)
                                .collect(Collectors.toList()));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova Tarefa", description = "Cria uma nova tarefa com base nos dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso")
    })
    public ResponseEntity<TarefaResponse> criarTarefa(@RequestBody  TarefaRequest tarefaRequest) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefaMapper.toEntity(tarefaRequest));
        return ResponseEntity.ok(tarefaMapper.toResponse(novaTarefa));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma Tarefa existente", description = "Atualiza os dados de uma tarefa existente com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    })
    public ResponseEntity<TarefaResponse> atualizarTarefa(@PathVariable Long id, 
                                                          @RequestBody TarefaRequest tarefaRequest) {
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefaMapper.toEntity(tarefaRequest));
        return ResponseEntity.ok(tarefaMapper.toResponse(tarefaAtualizada));
    } 

    @DeleteMapping ("/{id}")
    @Operation(summary = "Deleta uma Tarefa", description = "Deleta uma tarefa existente com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso")
    })
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}