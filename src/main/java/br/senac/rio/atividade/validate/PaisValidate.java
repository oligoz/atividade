package br.senac.rio.atividade.validate;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;

public class PaisValidate {
    
    /**
     * Validates the descricao of the pais.
     * @param descricao the descricao to validate
     * @throws AtributeNotValidException if the descricao is null or empty
     */
    public static void validateDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new AtributeNotValidException("Descrição do país não pode ser vazia.");
        }
    }
}
