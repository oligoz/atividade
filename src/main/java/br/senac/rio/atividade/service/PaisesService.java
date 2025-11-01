package br.senac.rio.atividade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.senac.rio.atividade.exceptions.ResourceNotFoundException;
import br.senac.rio.atividade.model.Pais;
import br.senac.rio.atividade.repository.PaisesRepository;
import br.senac.rio.atividade.validate.PaisValidate;

@Service
public class PaisesService {

    private final PaisesRepository paisesRepository;

    /**
     * Constructor for PaisesService.
     * @param paisesRepository the repository for paises
     */
    public PaisesService(PaisesRepository paisesRepository) {
        this.paisesRepository = paisesRepository;
    }

    /**
     * Retrieves all paises from the repository.
     * @return a list of all paises
     * @throws ResourceNotFoundException if no paises are found
     */
    public List<Pais> getAllPaises() {
        List<Pais> paises = paisesRepository.findAll();
        if (paises.isEmpty()) {
            throw new ResourceNotFoundException("No paises registered.");
        }
        return paises;
    }

    /**
     * Retrieves a pais by its ID.
     * @param id the ID of the pais
     * @return the pais with the specified ID
     * @throws ResourceNotFoundException if the pais is not found
     */
    public Pais getPaisById(Long id) {
        Optional<Pais> pais = paisesRepository.findById(id);
        if (pais.isEmpty()) {
            throw new ResourceNotFoundException("Pais not found with id: " + id);
        }
        return pais.get();
    }

    /**
     * Includes a new pais in the repository.
     * @param pais the pais to be included
     * @return the saved pais
     * @throws AtributeNotValidException if any attribute of the pais is invalid
     */
    public Pais includePais(Pais pais) {
        PaisValidate.validateDescricao(pais.getDescricao());
        return paisesRepository.save(pais);
    }

    /**
     * Updates an existing pais in the repository.
     * @param id the ID of the pais to be updated
     * @param pais the pais object containing updated attributes
     * @return the updated pais
     * @throws ResourceNotFoundException if the pais is not found
     * @throws AtributeNotValidException if any attribute of the pais is invalid
     */
    public Pais updatePais(Long id, Pais pais) {
        Pais existingPais = getPaisById(id);

        PaisValidate.validateDescricao(pais.getDescricao());

        existingPais.setDescricao(pais.getDescricao());

        return paisesRepository.save(existingPais);
    }

    /**
     * Deletes a pais by its ID.
     * @param id the ID of the pais to be deleted
     * @throws ResourceNotFoundException if the pais is not found
     */
    public void deletePais(Long id) {
        getPaisById(id);
        paisesRepository.deleteById(id);
    }
}
