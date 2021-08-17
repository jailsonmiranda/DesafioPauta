package com.desafiopautajailson.demo.api.model.entidade_autditavel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class EntidadeAuditavel implements Serializable {
    private static long serialVersionUID = 1L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "dt_criacao")
    private LocalDateTime dtCriacao;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "dt_alteracao")
    private LocalDateTime dtAlteracao;

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
    }

}
