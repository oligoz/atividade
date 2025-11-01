package br.senac.rio.atividade.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;
import br.senac.rio.atividade.exceptions.ResourceNotFoundException;
import br.senac.rio.atividade.model.Editora;
import br.senac.rio.atividade.service.EditorasService;

@RestController
@RequestMapping("/editoras")
public class EditorasController {

    private final EditorasService editorasService;

    /**
     * Constructor for EditorasController.
     * @param editorasService the service for editoras
     */
    public EditorasController(EditorasService editorasService) {
        this.editorasService = editorasService;
    }

    /**
     * Lists all editoras.
     * @return a ResponseEntity containing the list of editoras or an error message
     */
    @RequestMapping("/list")
    public ResponseEntity<?> listEditoras() {
        try {
            List<Editora> editoras = editorasService.getAllEditoras();
            return ResponseEntity.ok(editoras);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Retrieves an editora by its ID.
     * @param id the ID of the editora
     * @return a ResponseEntity containing the editora or an error message
     */
    @RequestMapping("/{id}")
    public ResponseEntity<?> getEditoraById(@PathVariable Long id) {
        try {
            Editora editora = editorasService.getEditoraById(id);
            return ResponseEntity.ok(editora);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Includes a new editora.
     * @param editora the editora to be included
     * @return a ResponseEntity containing the saved editora or an error message
     */
    @PostMapping("/include")
    public ResponseEntity<?> includeEditora(@RequestBody Editora editora) {
        try {
            Editora savedEditora = editorasService.includeEditora(editora);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEditora);
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Updates an existing editora.
     * @param id the ID of the editora to be updated
     * @param editora the editora object containing updated attributes
     * @return a ResponseEntity containing the updated editora or an error message
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEditora(@PathVariable Long id, @RequestBody Editora editora) {
        try {
            Editora updatedEditora = editorasService.updateEditora(id, editora);
            return ResponseEntity.ok(updatedEditora);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Deletes an editora by its ID.
     * @param id the ID of the editora to be deleted
     * @return a ResponseEntity indicating success or failure
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEditora(@PathVariable Long id) {
        try {
            editorasService.deleteEditora(id);
            return ResponseEntity.ok("Editora deleted successfully.");
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }
}
