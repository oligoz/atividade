package br.senac.rio.atividade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long codigo;

    @Getter
    @Setter
    private String descricao;

    public Pais() {
    }

    public Pais(String descricao) {
        this.descricao = descricao;
    }
}
