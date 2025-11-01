package com.senac.rio.atividade.repository;

import com.senac.rio.atividade.model.Linguas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinguasRepository extends JpaRepository<Linguas, Long> {
}
