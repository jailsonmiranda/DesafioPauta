package com.desafiopautajailson.demo.api.resource.associado;

import com.desafiopautajailson.demo.api.model.associado.Associado;
import com.desafiopautajailson.demo.api.model.pauta.Pauta;
import com.desafiopautajailson.demo.api.repository.associado.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/associado")
public class AssociadoResource {

    @Autowired
    private AssociadoRepository associadoRepository;

    @PostMapping
    public ResponseEntity<Associado> criarAssociado(@RequestBody Associado associado, HttpServletResponse response) {
        Associado associadoSalvo = associadoRepository.save(associado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(associado.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(associadoSalvo);
    }
}

