package br.edu.fatecpg.springweb.repository;

import br.edu.fatecpg.springweb.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
