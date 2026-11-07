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

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;
    private final TarefaMapper tarefaMapper;

    public TarefaController(TarefaService tarefaService, TarefaMapper tarefaMapper) {
        this.tarefaService = tarefaService;
        this.tarefaMapper = tarefaMapper;
    }

    @GetMapping 
    public ResponseEntity<List<TarefaResponse>> getTarefaService() {
        return ResponseEntity.ok(tarefaService.listarTarefas()
                                .stream().map(tarefaMapper::toResponse)
                                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> criarTarefa(@RequestBody  TarefaRequest tarefaRequest) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefaMapper.toEntity(tarefaRequest));
        return ResponseEntity.ok(tarefaMapper.toResponse(novaTarefa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizarTarefa(@PathVariable Long id, 
                                                          @RequestBody TarefaRequest tarefaRequest) {
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefaMapper.toEntity(tarefaRequest));
        return ResponseEntity.ok(tarefaMapper.toResponse(tarefaAtualizada));
    } 

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}