package com.rocha.tarefas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rocha.tarefas.domain.Tarefa;
import com.rocha.tarefas.repository.TarefaRepository;

@Service 
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    // Listar todas as tarefas
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // Criar uma nova tarefa
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Atualizar uma tarefa existente
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefaExistente = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));

        tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
        tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
        tarefaExistente.setConcluida(tarefaAtualizada.isConcluida());

        return tarefaRepository.save(tarefaExistente);
    }

    // Deletar uma tarefa pelo ID
    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
