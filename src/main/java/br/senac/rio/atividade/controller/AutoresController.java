package br.senac.rio.atividade.controller;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;
import br.senac.rio.atividade.exceptions.ResourceNotFoundException;
import br.senac.rio.atividade.model.Autor;
import br.senac.rio.atividade.model.View;
import br.senac.rio.atividade.service.AutoresService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutoresController {

    private final AutoresService autoresService;

    /**
     * Constructor for AutoresController.
     * @param autoresService the service for autores
     */
    public AutoresController(AutoresService autoresService) {
        this.autoresService = autoresService;
    }

    /**
     * Lists all autores.
     * @return a ResponseEntity containing the list of autores or an error message
     */
    @JsonView(View.Simple.class)
    @RequestMapping("/list")
    public ResponseEntity<?> listAutores() {
        try {
            List<Autor> autores = autoresService.getAllAutores();
            return ResponseEntity.ok(autores);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Lists all autores with full details.
     * @return a ResponseEntity containing the list of autores with full details or an error message
     */
    @JsonView(View.Full.class)
    @RequestMapping("/listfull")
    public ResponseEntity<?> listAutoresFull() {
        try {
            List<Autor> autores = autoresService.getAllAutoresFull();
            return ResponseEntity.ok(autores);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Retrieves an autor by its ID.
     * @param id the ID of the autor
     * @return a ResponseEntity containing the autor or an error message
     */
    @JsonView(View.Simple.class)
    @RequestMapping("/{id}")
    public ResponseEntity<?> getAutorById(@PathVariable Long id) {
        try {
            Autor autor = autoresService.getAutorById(id);
            return ResponseEntity.ok(autor);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Retrieves an autor by its ID with full details.
     * @param id the ID of the autor
     * @return a ResponseEntity containing the autor with full details or an error message
     */
    @JsonView(View.Full.class)
    @RequestMapping("/{id}/full")
    public ResponseEntity<?> getAutorFullById(@PathVariable Long id) {
        try {
            Autor autor = autoresService.getAutorFullById(id);
            return ResponseEntity.ok(autor);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Includes a new autor.
     * @param autor the autor to be included
     * @return a ResponseEntity containing the saved autor or an error message
     */
    @RequestMapping("/include")
    public ResponseEntity<?> includeAutor(@RequestBody Autor autor) {
        try {
            Autor novoAutor = autoresService.includeAutor(autor);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAutor);
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Updates an existing autor.
     * @param id the ID of the autor to be updated
     * @param autorAtualizado the autor object containing updated attributes
     * @return a ResponseEntity containing the updated autor or an error message
     */
    @RequestMapping("/update/{id}")
    public ResponseEntity<?> updateAutor(@PathVariable Long id, @RequestBody Autor autorAtualizado) {
        try {
            Autor autor = autoresService.updateAutor(id, autorAtualizado);
            return ResponseEntity.ok(autor);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Deletes an autor by its ID.
     * @param id the ID of the autor to be deleted
     * @return a ResponseEntity indicating success or failure
     */
    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> deleteAutor(@PathVariable Long id) {
        try {
            autoresService.deleteAutor(id);
            return ResponseEntity.ok("Autor deleted successfully.");
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }
}
