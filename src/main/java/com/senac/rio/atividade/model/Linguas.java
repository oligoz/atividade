package com.senac.rio.atividade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public class Linguas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long codigo;

    @Getter
    @Setter
    private String descricao;

    public Linguas() {
    }

    public Linguas(String descricao) {
        this.descricao = descricao;
    }
}
