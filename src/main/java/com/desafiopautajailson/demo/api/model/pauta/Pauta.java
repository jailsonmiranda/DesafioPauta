package com.desafiopautajailson.demo.api.model.pauta;

import com.desafiopautajailson.demo.api.model.entidade_autditavel.EntidadeAuditavel;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "pauta")
public class Pauta extends EntidadeAuditavel implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "pauta_fechada")
    private Boolean pauta_fechada;

    @Column(name = "pauta_iniciada")
    private Boolean pauta_iniciada;

    @Column(name = "total_sim")
    private Long total_sim;

    @Column(name = "total_nao")
    private Long total_nao;

}
