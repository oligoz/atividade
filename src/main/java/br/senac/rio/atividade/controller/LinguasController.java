package br.senac.rio.atividade.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;
import br.senac.rio.atividade.exceptions.ResourceNotFoundException;
import br.senac.rio.atividade.model.Lingua;
import br.senac.rio.atividade.service.LinguasService;

@RestController
@RequestMapping("/linguas")
public class LinguasController {

    private final LinguasService linguasService;

    /**
     * Constructor for LinguasController.
     * @param linguasService the service for linguas
     */
    public LinguasController(LinguasService linguasService) {
        this.linguasService = linguasService;
    }

    /**
     * Lists all linguas.
     * @return a ResponseEntity containing the list of linguas or an error message
     */
    @RequestMapping("/list")
    public ResponseEntity<?> listLinguas() {
        try {
            List<Lingua> linguas = linguasService.getAllLinguas();
            return ResponseEntity.ok(linguas);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Retrieves a lingua by its ID.
     * @param id the ID of the lingua
     * @return a ResponseEntity containing the lingua or an error message
     */
    @RequestMapping("/{id}")
    public ResponseEntity<?> getLinguaById(@PathVariable Long id) {
        try {
            Lingua lingua = linguasService.getLinguaById(id);
            return ResponseEntity.ok(lingua);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    /**
     * Includes a new lingua.
     * @param lingua the lingua to be included
     * @return a ResponseEntity containing the saved lingua or an error message
     */
    @RequestMapping("/include")
    public ResponseEntity<?> includeLingua(@RequestBody Lingua lingua) {
        try {
            Lingua savedLingua = linguasService.includeLingua(lingua);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLingua);
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Updates an existing lingua.
     * @param id the ID of the lingua to be updated
     * @param lingua the lingua object containing updated attributes
     * @return a ResponseEntity containing the updated lingua or an error message
     */
    @RequestMapping("/update/{id}")
    public ResponseEntity<?> updateLingua(@PathVariable Long id, @RequestBody Lingua lingua) {
        try {
            Lingua updatedLingua = linguasService.updateLingua(id, lingua);
            return ResponseEntity.ok(updatedLingua);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Deletes a lingua by its ID.
     * @param id the ID of the lingua to be deleted
     * @return a ResponseEntity indicating success or failure
     */
    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> deleteLingua(@PathVariable Long id) {
        try {
            linguasService.deleteLingua(id);
            return ResponseEntity.ok("Lingua deleted successfully.");
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }
}
