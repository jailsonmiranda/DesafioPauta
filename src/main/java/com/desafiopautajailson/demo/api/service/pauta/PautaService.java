package com.desafiopautajailson.demo.api.service.pauta;

import com.desafiopautajailson.demo.api.model.pauta.Pauta;
import com.desafiopautajailson.demo.api.repository.pauta.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PautaService {

    @Autowired
    public PautaRepository pautaRepository;

    @Transactional
    public Pauta iniciarSecao(Long pautaId) {
        Pauta pauta = pautaRepository.getById(pautaId);
        pauta.setPauta_iniciada(true);
        return pautaRepository.save(pauta);
    }

    @Transactional
    public Pauta fecharSecao(Long pautaId) {
        Pauta pauta = pautaRepository.getById(pautaId);
        pauta.setPauta_fechada(true);
        return  pautaRepository.save(pauta);
    }


}
