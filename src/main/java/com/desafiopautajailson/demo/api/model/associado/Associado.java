package com.desafiopautajailson.demo.api.model.associado;

import com.desafiopautajailson.demo.api.model.entidade_autditavel.EntidadeAuditavel;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "associado")
public class Associado extends EntidadeAuditavel implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nomecompleto")
    private String nomecompleto;

    @NotNull
    @Column(name = "cpfcnpj")
    private Long cpfcnpj;

}
