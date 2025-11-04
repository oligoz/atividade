package br.senac.rio.atividade.service;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;
import br.senac.rio.atividade.exceptions.ResourceNotFoundException;
import br.senac.rio.atividade.model.Autor;
import br.senac.rio.atividade.model.View;
import br.senac.rio.atividade.repository.AutoresRepository;
import br.senac.rio.atividade.validate.AutorValidate;
import br.senac.rio.atividade.validate.PaisValidate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoresService {

    private final AutoresRepository autoresRepository;
    private final PaisesService paisesService;

    /**
     * Constructor for AutoresService.
     * @param {AutoresRepository} autoresRepository the repository for autores
     * @param {PaisesService} paisesService the service for paises
     */
    public AutoresService(AutoresRepository autoresRepository, PaisesService paisesService) {
        this.autoresRepository = autoresRepository;
        this.paisesService = paisesService;
        Math.abs(-5);
    }

    /**
     * Retrieves all autores from the repository.
     * @return a list of all autores
     * @throws ResourceNotFoundException if no autores are found
     */
    public List<Autor> getAllAutores() {
        List<Autor> autores = autoresRepository.findAll();
        if (autores.isEmpty()) {
            throw new ResourceNotFoundException("No autores registered.");
        }
        return autores;
    }

    /**
     * Retrieves all autores from the repository with full pais details.
     * @return a list of all autores with pais details
     * @throws ResourceNotFoundException if no autores are found
     */
    public List<Autor> getAllAutoresFull() {
        List<Autor> autores = autoresRepository.findAll();
        if (autores.isEmpty()) {
            throw new ResourceNotFoundException("No autores registered.");
        } else {
            for (Autor autor : autores) {
                if (autor.getPais_codigo() != null) {
                    autor.setPais(paisesService.getPaisById(autor.getPais_codigo()));
                }
            }
        }
        return autores;
    }

    /**
     * Retrieves an autor by its ID.
     * @param id the ID of the autor
     * @return the autor with the specified ID
     * @throws ResourceNotFoundException if the autor is not found
     */
    public Autor getAutorById(Long id) {
        Optional<Autor> autor = autoresRepository.findById(id);
        if (autor.isEmpty()) {
            throw new ResourceNotFoundException("Autor not found with id: " + id);
        }
        return autor.get();
    }

    /**
     * Retrieves an autor by its ID with full pais details.
     * @param id the ID of the autor
     * @return the autor with the specified ID and pais details
     * @throws ResourceNotFoundException if the autor is not found
     */
    public Autor getAutorFullById(Long id) {
        Optional<Autor> autor = autoresRepository.findById(id);
        if (autor.isEmpty()) {
            throw new ResourceNotFoundException("Autor not found with id: " + id);
        } else {
            Autor a = autor.get();
            if (a.getPais_codigo() != null) {
                a.setPais(paisesService.getPaisById(a.getPais_codigo()));
            }
            return a;
        }
    }

    /**
     * Includes a new autor in the repository after validating its attributes.
     * @param autor the autor to be included
     * @return the saved autor
     * @throws AtributeNotValidException if any attribute of the autor is invalid
     */
    public Autor includeAutor(Autor autor) {
        AutorValidate.validateNome(autor.getNome());
        AutorValidate.validateDataNascimento(autor.getDatanascimento());
//        AutorValidate.validatePaisCodigo(autor.getPais_codigo());
        if (autor.getPais_codigo() != null) {
            paisesService.getPaisById(autor.getPais_codigo());
        }

        AutorValidate.validateNotaBiografica(autor.getNotabiografica());
        AutorValidate.validateSexo(autor.getSexo());
        return autoresRepository.save(autor);
    }

    /**
     * Updates an existing autor in the repository after validating its attributes.
     * @param id the ID of the autor to be updated
     * @param autorAtualizado the autor object containing updated attributes
     * @return the updated autor
     * @throws ResourceNotFoundException if the autor with the given ID does not exist
     * @throws AtributeNotValidException if any attribute of the updated autor is invalid
     */
    public Autor updateAutor(Long id, Autor autorAtualizado) {
        Autor autorExistente = getAutorById(id);

        AutorValidate.validateNome(autorAtualizado.getNome());
        AutorValidate.validateDataNascimento(autorAtualizado.getDatanascimento());
        if (autorAtualizado.getPais_codigo() != null) {
            paisesService.getPaisById(autorAtualizado.getPais_codigo());
        }
        AutorValidate.validateNotaBiografica(autorAtualizado.getNotabiografica());
        AutorValidate.validateSexo(autorAtualizado.getSexo());

        autorExistente.setNome(autorAtualizado.getNome());
        autorExistente.setDatanascimento(autorAtualizado.getDatanascimento());
        autorExistente.setPais_codigo(autorAtualizado.getPais_codigo());
        autorExistente.setNotabiografica(autorAtualizado.getNotabiografica());
        autorExistente.setSexo(autorAtualizado.getSexo());

        return autoresRepository.save(autorExistente);
    }

    /**
     * Deletes an autor from the repository by its ID.
     * @param id the ID of the autor to be deleted
     * @throws ResourceNotFoundException if the autor with the given ID does not exist
     */
    public void deleteAutor(Long id) {
        getAutorById(id);
        autoresRepository.deleteById(id);
    }
}
