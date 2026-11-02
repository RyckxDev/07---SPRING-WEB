package br.edu.fatecpg.springweb.controller;

import br.edu.fatecpg.springweb.model.Tarefa;
import br.edu.fatecpg.springweb.repository.TarefaRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	private final TarefaRepository repository;

	public TarefaController(TarefaRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tarefa criar(@Valid @RequestBody Tarefa tarefa) {
		tarefa.setId(null);
		return repository.save(tarefa);
	}

	@GetMapping
	public List<Tarefa> listar() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
		return repository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa dados) {
		return repository.findById(id).map(tarefa -> {
			tarefa.setDescricao(dados.getDescricao());
			tarefa.setConcluida(dados.getConcluida());
			tarefa.setPrioridade(dados.getPrioridade());
			return ResponseEntity.ok(repository.save(tarefa));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
