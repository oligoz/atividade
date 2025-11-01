package br.senac.rio.atividade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.senac.rio.atividade.exceptions.ResourceNotFoundException;
import br.senac.rio.atividade.model.Lingua;
import br.senac.rio.atividade.repository.LinguasRepository;
import br.senac.rio.atividade.validate.LinguaValidate;

@Service
public class LinguasService {
    private final LinguasRepository linguasRepository;

    /**
     * Constructor for LinguasService.
     * @param linguasRepository the repository for linguas
     */
    public LinguasService(LinguasRepository linguasRepository) {
        this.linguasRepository = linguasRepository;
    }

    /**
     * Retrieves all linguas from the repository.
     * @return a list of all linguas
     * @throws ResourceNotFoundException if no linguas are found
     */
    public List<Lingua> getAllLinguas() {
        List<Lingua> linguas = linguasRepository.findAll();
        if (linguas.isEmpty()) {
            throw new ResourceNotFoundException("No linguas registered.");
        }
        return linguas;
    }

    /**
     * Retrieves a lingua by its ID.
     * @param id the ID of the lingua
     * @return the lingua with the specified ID
     * @throws ResourceNotFoundException if the lingua is not found
     */
    public Lingua getLinguaById(Long id) {
        Optional<Lingua> lingua = linguasRepository.findById(id);
        if (lingua.isEmpty()) {
            throw new ResourceNotFoundException("Lingua not found with id: " + id);
        }
        return lingua.get();
    }

    /**
     * Includes a new lingua in the repository.
     * @param lingua the lingua to be included
     * @return the saved lingua
     * @throws AtributeNotValidException if any attribute of the lingua is invalid
     */
    public Lingua includeLingua(Lingua lingua) {
        LinguaValidate.validateDescricao(lingua.getDescricao());
        return linguasRepository.save(lingua);
    }

    /**
     * Updates an existing lingua in the repository.
     * @param id the ID of the lingua to be updated
     * @param linguaAtualizada the lingua object containing updated attributes
     * @return the updated lingua
     * @throws ResourceNotFoundException if the lingua is not found
     * @throws AtributeNotValidException if any attribute of the lingua is invalid
     */
    public Lingua updateLingua(Long id, Lingua linguaAtualizada) {
        Lingua linguaExistente = getLinguaById(id);

        LinguaValidate.validateDescricao(linguaAtualizada.getDescricao());

        linguaExistente.setDescricao(linguaAtualizada.getDescricao());

        return linguasRepository.save(linguaExistente);
    }

    /**
     * Deletes a lingua by its ID.
     * @param id the ID of the lingua to be deleted
     * @throws ResourceNotFoundException if the lingua is not found
     */
    public void deleteLingua(Long id) {
        getLinguaById(id);
        linguasRepository.deleteById(id);
    }
}
