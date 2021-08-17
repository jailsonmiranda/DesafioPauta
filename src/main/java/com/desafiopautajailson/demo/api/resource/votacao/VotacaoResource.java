package com.desafiopautajailson.demo.api.resource.votacao;

import com.desafiopautajailson.demo.api.model.votacao.Votacao;
import com.desafiopautajailson.demo.api.repository.votacao.VotacaoRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Log
@RestController
@RequestMapping("/voto")
public class VotacaoResource {
    @Autowired
    private VotacaoRepository votacaoRepository;

    /*
     @Autowired
    private AssociadoFeignClient associadoFeignClient;

    @Autowired
    private PautaFeignClient pautaFeignClient;

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;
    */

    @PostMapping
    public ResponseEntity<Votacao> votoAssociado(@RequestBody Votacao votacao, HttpServletResponse response){
/*
        Optional<Pauta> pauta = Optional.of(pautaFeignClient.findById(votacao.getId()).getBody());
        pauta.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + votacao.getId() + ", Tipo: " + Pauta.class.getName()));

        if(pauta.get().getPauta_iniciada().equals(true)) {
            throw new SessaoParaVotoEncerradaException("Sessão para votação encerrada ou não iniciada! Pauta: " + pauta.get().getId());
        }

        // - validar se associado tem permissão para votar nesta pauta
        Votacao entity = new Votacao();
        Optional<UserInfo> userInfo = Optional.of(userInfoFeignClient.autorizaCpf(votacao.getAssociado().getCpfcnpj().toString()));
        log.info("USER-INFO: " + userInfo.toString());

        if(userInfo.get().getStatus().toString() == "UNABLE_TO_VOTE") {
            throw new AssociadoNaoTemPermissaoVotarNestaPautaException("Voto nao permitido para este CPF! UserInfo: " + votacao.getAssociado().getCpfcnpj().toString() + ", Tipo: " + Associado.class.getName());
        }

        Optional<Associado> associado = Optional.of(associadoFeignClient.findByCpf(votacao.getAssociado().getCpfcnpj().toString()).getBody());
        associado.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado! CPF: " + votacao.getAssociado().getCpfcnpj().toString() + ", Tipo: " + Associado.class.getName()));

        Optional<Votacao> voto = votacaoRepository.findByCpfAssociado(votacao.getAssociado().getCpfcnpj().toString());
        if(voto.isPresent()) {

            if (voto.get().getAssociado().getId().compareTo(votacao.getPauta().getId()) > 0) {
                throw new AssociadoJaVotouNestaPautaException("Associado ja votou nesta pauta! CPF: " + votacao.getAssociado().getCpfcnpj().toString() + ", Tipo: " + Associado.class.getName());
            }
        }
*/
        //Gravação do voto se validou anteriormente que se encaixa nos requisitos de poder votar:
        Votacao votacaoSalva = votacaoRepository.save(votacao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(votacao.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(votacaoSalva);
    }

    /*
    @PostMapping("/vendaCondicional/{movimentoEntradaSaidaEstoqueId}")
    public ResponseEntity<MovimentoEntradaSaidaEstoque> vendaCondicional(@PathVariable Long movimentoEntradaSaidaEstoqueId, @RequestBody MovimentoEntradaSaidaEstoque movimentoEntradaSaidaEstoque) {
        return ResponseEntity.ok(movimentoEntradaSaidaEstoqueService.vendaCondicional(movimentoEntradaSaidaEstoqueId, movimentoEntradaSaidaEstoque));
    }

 */
}
