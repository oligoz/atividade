package br.senac.rio.atividade.model;

import br.senac.rio.atividade.service.PaisesService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name= "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @JsonView(View.Simple.class)
    private Long codigo;

    @Getter
    @Setter
    @JsonView(View.Simple.class)
    private String nome;

    @Getter
    @Setter
    @JsonView(View.Simple.class)
    private Date datanascimento;

    @Getter
    @Setter
    @JsonView(View.Simple.class)
    private Long pais_codigo;

    @Getter
    @Setter
    @JsonView(View.Simple.class)
    private String notabiografica;

    @Getter
    @Setter
    @JsonView(View.Simple.class)
    private char sexo;

    @Transient
    @Getter
    @Setter
    @JsonView(View.Full.class)
    private Pais pais;

    public Autor() {
    }

    public Autor(String nome, Date datanascimento, Long pais_codigo, String notabiobrafica, char sexo) {
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.pais_codigo = pais_codigo;
        this.notabiografica = notabiobrafica;
        this.sexo = sexo;
    }

//    public void setPais_codigo(Long pais_codigo) {
//        this.pais_codigo = pais_codigo;
//        if (pais_codigo != null) {
//            PaisesService paisesService = new PaisesService(null);
//            this.pais = paisesService.getPaisById(pais_codigo);
//        }
//    }
}
