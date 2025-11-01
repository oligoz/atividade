package br.senac.rio.atividade.validate;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;

public class LinguaValidate {
    
    /**
     * Validates the description of the language.
     * @param descricao the description to validate
     * @throws AtributeNotValidException if the description is null or empty
     */
    public static void validateDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new AtributeNotValidException("Descricao da língua não pode ser vazio.");
        }
    }
}
