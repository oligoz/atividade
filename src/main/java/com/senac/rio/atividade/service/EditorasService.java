package com.senac.rio.atividade.service;

import com.senac.rio.atividade.model.Editoras;
import com.senac.rio.atividade.repository.EditorasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorasService {

    private final EditorasRepository editorasRepository;

    public EditorasService(EditorasRepository editorasRepository) {
        this.editorasRepository = editorasRepository;
    }

    public List<Editoras> getAllEditoras() {
        return editorasRepository.findAll();
    }
}
