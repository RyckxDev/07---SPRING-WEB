package br.edu.fatecpg.springweb.controller;

import br.edu.fatecpg.springweb.model.Livro;
import br.edu.fatecpg.springweb.repository.LivroRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private final LivroRepository repository;

	public LivroController(LivroRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Livro criar(@Valid @RequestBody Livro livro) {
		livro.setId(null);
		return repository.save(livro);
	}

	@GetMapping
	public List<Livro> listar() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscar(@PathVariable Long id) {
		return repository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro dados) {
		return repository.findById(id).map(livro -> {
			livro.setTitulo(dados.getTitulo());
			livro.setAutor(dados.getAutor());
			livro.setIsbn(dados.getIsbn());
			livro.setAnoPublicacao(dados.getAnoPublicacao());
			return ResponseEntity.ok(repository.save(livro));
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
