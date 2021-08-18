package com.desafiopautajailson.demo.api.repository.votacao;


import com.desafiopautajailson.demo.api.model.votacao.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

}
