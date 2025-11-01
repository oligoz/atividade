package com.senac.rio.atividade.controller;


import com.senac.rio.atividade.model.Editoras;
import com.senac.rio.atividade.service.EditorasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditorasController {

    private final EditorasService editorasService;

    public EditorasController(EditorasService editorasService) {
        this.editorasService = editorasService;
    }

    @RequestMapping("/list")
    public List<Editoras> listEditoras() {
        return editorasService.getAllEditoras();
    }
}
