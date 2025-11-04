package br.senac.rio.atividade.model;

import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(View.Full.class)
    private Long codigo;

    @Getter
    @Setter
    @JsonView(View.Full.class)
    private String descricao;

    public Pais() {
    }

    public Pais(String descricao) {
        this.descricao = descricao;
    }
}
