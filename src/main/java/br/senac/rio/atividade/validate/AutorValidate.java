package br.senac.rio.atividade.validate;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;
import br.senac.rio.atividade.service.PaisesService;

import java.util.Date;

public class AutorValidate {

    private final PaisesService paisesService;
    public AutorValidate(PaisesService paisesService) {
        this.paisesService = paisesService;
    }

    public static void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new AtributeNotValidException("Nome do autor não pode ser vazio.");
        }
    }

    public static void validateDataNascimento(Date datanascimento) {
        if (datanascimento == null) {
            throw new AtributeNotValidException("Data de nascimento do autor não pode ser nula.");
        } else if (datanascimento.after(new Date())) {
            throw new AtributeNotValidException("Data de nascimento do autor não pode ser no futuro.");
        }
    }

    public static void validateNotaBiografica(String notabiografica) {
        if (notabiografica == null || notabiografica.trim().isEmpty()) {
            throw new AtributeNotValidException("Nota biográfica do autor não pode ser vazia.");
        }
    }

    public static void validateSexo(char sexo) {
        if (sexo != 'M' && sexo != 'F') {
            throw new AtributeNotValidException("Sexo do autor deve ser 'M' ou 'F'.");
        }
    }
}
