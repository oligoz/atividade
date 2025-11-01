package com.senac.rio.atividade.repository;

import com.senac.rio.atividade.model.Editoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorasRepository extends JpaRepository<Editoras, Long> {
}
