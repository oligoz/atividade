package br.senac.rio.atividade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "linguas")
public class Lingua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long codigo;

    @Getter
    @Setter
    private String descricao;

    public Lingua() {
    }

    public Lingua(String descricao) {
        this.descricao = descricao;
    }
}
