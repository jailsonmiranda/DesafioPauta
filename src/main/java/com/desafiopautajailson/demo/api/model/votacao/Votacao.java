package com.desafiopautajailson.demo.api.model.votacao;

import com.desafiopautajailson.demo.api.model.associado.Associado;
import com.desafiopautajailson.demo.api.model.entidade_autditavel.EntidadeAuditavel;
import com.desafiopautajailson.demo.api.model.pauta.Pauta;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "votacao")
public class Votacao extends EntidadeAuditavel implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "id_associado")
    private Associado associado;

    @Column(name = "flag_votou_sim")
    private Boolean flag_votou_sim;

}
