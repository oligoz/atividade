package br.senac.rio.atividade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.senac.rio.atividade.exceptions.ResourceNotFoundException;
import br.senac.rio.atividade.model.Editora;
import br.senac.rio.atividade.repository.EditorasRepository;
import br.senac.rio.atividade.validate.EditoraValidate;

@Service
public class EditorasService {

    private final EditorasRepository editorasRepository;

    /**
     * Constructor for EditorasService.
     * @param editorasRepository the repository for editoras
     */
    public EditorasService(EditorasRepository editorasRepository) {
        this.editorasRepository = editorasRepository;
    }

    /**
     * Retrieves all editoras from the repository.
     * @return a list of all editoras
     * @throws ResourceNotFoundException if no editoras are found
     */
    public List<Editora> getAllEditoras() {
        List<Editora> editoras = editorasRepository.findAll();
        if (editoras.isEmpty()) {
            throw new ResourceNotFoundException("No editoras registered.");
        }
        return editoras;
    }

    /**
     * Retrieves an editora by its ID.
     * @param id the ID of the editora
     * @return the editora with the specified ID
     * @throws ResourceNotFoundException if the editora is not found
     */
    public Editora getEditoraById(Long id) {
        Optional<Editora> editora = editorasRepository.findById(id);
        if (editora.isEmpty()) {
            throw new ResourceNotFoundException("Editora not found with id: " + id);
        }
        return editora.get();
    }

    /**
     * Includes a new editora in the repository after validating its attributes.
     * @param editora the editora to be included
     * @return the saved editora
     * @throws AtributeNotValidException if any attribute of the editora is invalid
     */
    public Editora includeEditora(Editora editora) {
        EditoraValidate.validateNome(editora.getNome());
        EditoraValidate.validateEndereco(editora.getEndereco());
        EditoraValidate.validateTelefone(editora.getTelefone());
        return editorasRepository.save(editora);
    }

    /**
     * Updates an existing editora in the repository after validating its attributes.
     * @param id the ID of the editora to be updated
     * @param editoraAtualizada the editora object containing updated attributes
     * @return the updated editora
     * @throws ResourceNotFoundException if the editora with the given ID does not exist
     * @throws AtributeNotValidException if any attribute of the updated editora is invalid
     */
    public Editora updateEditora(Long id, Editora editoraAtualizada) {
        Editora editoraExistente = getEditoraById(id);

        EditoraValidate.validateNome(editoraAtualizada.getNome());
        EditoraValidate.validateEndereco(editoraAtualizada.getEndereco());
        EditoraValidate.validateTelefone(editoraAtualizada.getTelefone());

        editoraExistente.setNome(editoraAtualizada.getNome());
        editoraExistente.setEndereco(editoraAtualizada.getEndereco());
        editoraExistente.setTelefone(editoraAtualizada.getTelefone());

        return editorasRepository.save(editoraExistente);
    }

    /**
     * Deletes an editora from the repository by its ID.
     * @param id the ID of the editora to be deleted
     * @throws ResourceNotFoundException if the editora with the given ID does not exist
     */
    public void deleteEditora(Long id) {
        getEditoraById(id);
        editorasRepository.deleteById(id);
    }
}
