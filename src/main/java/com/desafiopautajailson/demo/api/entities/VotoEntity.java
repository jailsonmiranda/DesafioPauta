package com.desafiopautajailson.demo.api.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "votacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoEntity implements Serializable {
    private static final  long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pautaId;
    private Long id_associado;
    private Boolean flag_votou_sim;
}
