package com.desafiopautajailson.demo.api.resource.votacao;

import com.desafiopautajailson.demo.api.dtos.PautaDTO;
import com.desafiopautajailson.demo.api.exceptions.AssociadoJaVotouNestaPautaException;
import com.desafiopautajailson.demo.api.exceptions.AssociadoNaoTemPermissaoVotarNestaPautaException;
import com.desafiopautajailson.demo.api.exceptions.SessaoParaVotoEncerradaException;
import com.desafiopautajailson.demo.api.model.associado.Associado;
import com.desafiopautajailson.demo.api.model.pauta.Pauta;
import com.desafiopautajailson.demo.api.model.votacao.Votacao;
import com.desafiopautajailson.demo.api.repository.pauta.PautaRepository;
import com.desafiopautajailson.demo.api.repository.votacao.VotacaoRepository;
import com.desafiopautajailson.demo.api.service.pauta.PautaService;
import com.desafiopautajailson.demo.api.service.votacao.VotacaoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@Log
@RestController
@RequestMapping("/voto")
public class VotacaoResource {
    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private VotacaoService votacaoService;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    public PautaService pautaService;

    @PostMapping
    public ResponseEntity<Votacao> votoAssociado(@RequestBody Votacao votacao, HttpServletResponse response){
        //Valida se votação esta encerrada por tempo:
        Pauta pauta = pautaRepository.findById(votacao.getPauta().getId()).get();
       // if (pauta.getDtCriacao().format() .equals(false)) {
       //     throw new SessaoParaVotoEncerradaException("Sessão de votação fechada por tempo excedido! Pauta: " + pauta.getId());
        //}

        //Valida se votação esta encerrada ou aberta:
        if (pauta.getPauta_iniciada().equals(false) || pauta.getPauta_fechada().equals(true)) {
            throw new SessaoParaVotoEncerradaException("Sessão de votação fechada ou não iniciada! Pauta: " + pauta.getId());
        }

        //Valida se votação esta encerrada ou aberta:
        List<Votacao> votacaoCpjCnpj = votacaoRepository.findAll();
        for(int i = 0 ; i < votacaoCpjCnpj.size(); i++){
            if (votacaoCpjCnpj.get(i).getAssociado().getId().equals(votacao.getAssociado().getId())) {
                throw new AssociadoNaoTemPermissaoVotarNestaPautaException("Voto nao permitido para este CPF! UserInfo: " + votacao.getAssociado().getCpfcnpj().toString() + ", Tipo: " + Associado.class.getName());
            }
        }

        if (pauta.getPauta_iniciada().equals(false) || pauta.getPauta_fechada().equals(true)) {
            throw new AssociadoJaVotouNestaPautaException("Associado ja votou nesta pauta! CPF: " + votacao.getAssociado().getCpfcnpj().toString() + ", Tipo: " + Associado.class.getName());
        }

        //Gravação do voto se validou anteriormente que se encaixa nos requisitos de poder votar:
        Votacao votacaoSalva = votacaoRepository.save(votacao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(votacao.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        //Atualiza contagem dos votos para posteriormente imprimir:
        Long countVotoSim = Long.valueOf(0);
        Long countVotoNao = Long.valueOf(0);
        if (votacao.getFlag_votou_sim().equals(true)) {
            countVotoSim = pauta.getTotal_sim() + Long.valueOf(1);
        }else {
            countVotoNao = pauta.getTotal_nao() + Long.valueOf(1);
        }
        pautaService.atualizarContagemVotos(votacao.getPauta().getId(), countVotoSim, countVotoNao);

        return ResponseEntity.created(uri).body(votacaoSalva);
    }

    @PostMapping(value = "/contabiliza/{pautaid}")
    public ResponseEntity<PautaDTO> contabilizaVoto(@PathVariable Long pautaid) {
        PautaDTO objDto = votacaoService.contabilizaVoto(pautaid);
        return ResponseEntity.ok(objDto);
    }
}
