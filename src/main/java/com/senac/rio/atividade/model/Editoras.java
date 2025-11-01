package com.senac.rio.atividade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "editoras")
public class Editoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long codigo;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String endereco;

    @Getter
    @Setter
    private String telefone;

    public Editoras() {
    }

    public Editoras(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
