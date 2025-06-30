package com.campanha.rpg.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.campanha.rpg.model.Campanha;
import com.campanha.rpg.model.Template;
import com.campanha.rpg.model.Vertice;
import com.campanha.rpg.repositories.CampanhaRepository;
import com.campanha.rpg.repositories.TemplateRepository;
import com.campanha.rpg.repositories.VerticeRepository;
import com.campanha.rpg.types.Pair;
import com.campanha.rpg.types.VerticeDTO;

@RestController
public class VerticeController {

   private final VerticeRepository repository;
   private final CampanhaRepository campanhaRepository;
   private final TemplateRepository templateRepository;

   public VerticeController(VerticeRepository repository, CampanhaRepository campanhaRepository, TemplateRepository templateRepository) {
       this.repository = repository;
       this.campanhaRepository = campanhaRepository;
       this.templateRepository = templateRepository;
   }

    @GetMapping("/vertices")
    List<Vertice> all() {
        return (List<Vertice>) repository.findAll();
    }

    @PostMapping("/vertices")
    Vertice newVertice(@RequestBody VerticeDTO verticeDTO) {
        Campanha campanha = campanhaRepository.findById(verticeDTO.campanhaId)
            .orElseThrow(() -> new RuntimeException("Campanha not found with id: " + verticeDTO.campanhaId));

        Template template = templateRepository.findById(verticeDTO.templateId)
            .orElseThrow(() -> new RuntimeException("Template not found with id: " + verticeDTO.templateId));

        if (!verticeDTO.caracteristicasInteiros.keySet().containsAll(template.getCaracteristicasInteiros())) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, 
                "Player doesn't include all template caracteristicasInteiros"
            );
        }

        if (!verticeDTO.caracteristicasString.keySet().containsAll(template.getCaracteristicasString())) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, 
                "Player doesn't include all template caracteristicasString"
            );
        }
        
        Vertice vertice = new Vertice(campanha, template, verticeDTO.descricao, verticeDTO.nome);
        for (java.util.Map.Entry<String, Pair> entry : verticeDTO.caracteristicasInteiros.entrySet()) {
            String chave = entry.getKey();
            Pair valor = entry.getValue();
            vertice.AdicionarCaracteristicaInteiros(chave, valor.getFirst(), valor.getSecond());
        }
        
        for (java.util.Map.Entry<String, String> entry : verticeDTO.caracteristicasString.entrySet()) {
            String chave = entry.getKey();
            String valor = entry.getValue();
            vertice.AdicionarCaracteristicaString(chave, valor);
        }

        for (Long vizinhoId : verticeDTO.vizinhos) {
            Vertice vizinho = repository.findById(vizinhoId)
                .orElseThrow(() -> new RuntimeException("Vertice vizinho not found with id: " + vizinhoId));
            vertice.AdicionarVizinho(vizinho);
            vizinho.AdicionarVizinho(vertice);
        }

        return repository.save(vertice);
    }

    // Single item

    @GetMapping("/vertices/{id}")
    Vertice one(@PathVariable Long id) {

        return repository.findById(id)
        .orElseThrow(() -> new Error("Vertice not found with id: " + id));
    }

    @PutMapping("/vertices/{id}")
    Vertice replaceVertice(@RequestBody Vertice newVertice, @PathVariable Long id) {

        return repository.findById(id)
        .map(template -> {
            template.setNome(newVertice.getNome());
            template.setDescricao(newVertice.getDescricao());
            return repository.save(template);
        })
        .orElseGet(() -> {
            return repository.save(newVertice);
        });
    }

    @DeleteMapping("/vertices/{id}")
    void deleteVertice(@PathVariable Long id) {
        repository.deleteById(id);
    }
}