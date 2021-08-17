package com.desafiopautajailson.demo.api.resource.pauta;

import com.desafiopautajailson.demo.api.model.pauta.Pauta;
import com.desafiopautajailson.demo.api.repository.pauta.PautaRepository;
import com.desafiopautajailson.demo.api.service.pauta.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/pauta")
public class PautaResource {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta, HttpServletResponse response){
        Pauta pautaSalva = pautaRepository.save(pauta);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
               .buildAndExpand(pauta.getId()).toUri();
       response.setHeader("Location", uri.toASCIIString());

       return ResponseEntity.created(uri).body(pautaSalva);
    }

    @PutMapping("/iniciarSecao/{id}")
    public ResponseEntity<Pauta> iniciarSecao(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.iniciarSecao(id));
    }

    @PutMapping("/fecharSecao/{id}")
    public ResponseEntity<Pauta> fecharSecao(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.fecharSecao(id));
    }

   // public ResponseEntity<Pauta> contabilizarVotosPauta(@PathVariable Long id){

//        return ResponseEntity.ok(pautaService.(id))
   // }
}
