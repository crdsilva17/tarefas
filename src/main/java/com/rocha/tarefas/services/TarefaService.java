package com.rocha.tarefas.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rocha.tarefas.model.Tarefa;
import com.rocha.tarefas.repository.TarefaRepository;

@Service 
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    // Listar todas as tarefas
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // Criar uma nova tarefa
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Atualizar uma tarefa existente
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefaExistente = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));

        tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
        tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
        tarefaExistente.setConcluida(tarefaAtualizada.isConcluida());

        return tarefaRepository.save(tarefaExistente);
    }

    // Deletar uma tarefa pelo ID
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletarTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada com o ID: " + id);
        }
        tarefaRepository.deleteById(id);
    }
}
