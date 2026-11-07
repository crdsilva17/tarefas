package com.rocha.tarefas.controllers;

import java.util.List;

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
import com.rocha.tarefas.services.TarefaService;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping 
    public ResponseEntity<List<Tarefa>> getTarefaService() {
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody  Tarefa tarefa) {
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefa);
    } 

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}