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
import br.senac.rio.atividade.model.Pais;
import br.senac.rio.atividade.service.PaisesService;

@RestController
@RequestMapping("/paises")
public class PaisesController {

    private final PaisesService paisesService;

    /**
     * Constructor for PaisesController.
     * @param paisesService the service for paises
     */
    public PaisesController(PaisesService paisesService) {
        this.paisesService = paisesService;
    }

    /**
     * Lists all paises.
     * @return a ResponseEntity containing the list of paises or an error message
     */
    @RequestMapping("/list")
    public ResponseEntity<?> listPaises() {
        try {
            List<Pais> paises = paisesService.getAllPaises();
            return ResponseEntity.ok(paises);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Retrieves a pais by its ID.
     * @param id the ID of the pais
     * @return a ResponseEntity containing the pais or an error message
     */
    @RequestMapping("/{id}")
    public ResponseEntity<?> getPaisById(@PathVariable Long id) {
        try {
            Pais pais = paisesService.getPaisById(id);
            return ResponseEntity.ok(pais);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Includes a new pais.
     * @param pais the pais to be included
     * @return a ResponseEntity containing the saved pais or an error message
     */
    @RequestMapping("/include")
    public ResponseEntity<?> includePais(@RequestBody Pais pais) {
        try {
            Pais savedPais = paisesService.includePais(pais);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPais);
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Updates an existing pais.
     * @param id the ID of the pais to be updated
     * @param pais the pais object containing updated attributes
     * @return a ResponseEntity containing the updated pais or an error message
     */
    @RequestMapping("/update/{id}")
    public ResponseEntity<?> updatePais(@PathVariable Long id, @RequestBody Pais pais) {
        try {
            Pais updatedPais = paisesService.updatePais(id, pais);
            return ResponseEntity.ok(updatedPais);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    /**
     * Deletes a pais by its ID.
     * @param id the ID of the pais to be deleted
     * @return a ResponseEntity indicating success or failure
     */
    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> deletePais(@PathVariable Long id) {
        try {
            paisesService.deletePais(id);
            return ResponseEntity.ok("Pais deleted successfully.");
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }
}
