package br.senac.rio.atividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senac.rio.atividade.model.Pais;

@Repository
public interface PaisesRepository extends JpaRepository<Pais, Long> {
}
