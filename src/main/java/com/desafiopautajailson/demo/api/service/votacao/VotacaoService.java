package com.desafiopautajailson.demo.api.service.votacao;

import com.desafiopautajailson.demo.api.dtos.PautaDTO;
import com.desafiopautajailson.demo.api.exceptions.ObjetoNaoEncontradoException;
import com.desafiopautajailson.demo.api.model.pauta.Pauta;
import com.desafiopautajailson.demo.api.repository.pauta.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotacaoService {

    @Autowired
    private PautaRepository pautaRepository;

    public PautaDTO contabilizaVoto(Long pautaId) {

        Pauta pauta = pautaRepository.findById(pautaId).get();
        if (pauta.getId() <= 0) {
            throw new ObjetoNaoEncontradoException("Pauta não encontrada! Id: " + pautaId + ", Tipo: " + Pauta.class.getName());
        }

        Long votoSim = pauta.getTotal_sim();
        Long votoNao = pauta.getTotal_nao();

        PautaDTO obj = new PautaDTO(pauta.getId(), pauta.getDescricao(), votoSim, votoNao);

        return obj;
    }
}
