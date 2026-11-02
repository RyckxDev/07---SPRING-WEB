package br.edu.fatecpg.springweb.repository;

import br.edu.fatecpg.springweb.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
