package br.senac.rio.atividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senac.rio.atividade.model.Lingua;

@Repository
public interface LinguasRepository extends JpaRepository<Lingua, Long> {
}
