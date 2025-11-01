package br.senac.rio.atividade.validate;

import br.senac.rio.atividade.exceptions.AtributeNotValidException;

public class EditoraValidate {
    
    /**
     * Validates the name of the editora.
     * @param nome the name to validate
     * @throws AtributeNotValidException if the name is null or empty
     */
    public static void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new AtributeNotValidException("Nome da editora não pode ser vazio.");
        }
    }

    /**
     * Validates the telefone of the editora.
     * @param telefone the telefone to validate
     * @throws AtributeNotValidException if the telefone is null or does not have 9 digits
     */
    public static void validateTelefone(String telefone) {
        if (telefone == null || !telefone.matches("\\d{9}\s*")) {
            throw new AtributeNotValidException("Telefone da editora deve ter 9 dígitos.");
        }
    }

    /**
     * Validates the endereco of the editora.
     * @param endereco the endereco to validate
     * @throws AtributeNotValidException if the endereco is null or empty
     */
    public static void validateEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new AtributeNotValidException("Endereço da editora não pode ser vazio.");
        }
    }
}
