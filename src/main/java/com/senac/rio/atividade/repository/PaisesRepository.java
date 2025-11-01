package com.senac.rio.atividade.repository;

import com.senac.rio.atividade.model.Paises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisesRepository extends JpaRepository<Paises, Long> {
}
