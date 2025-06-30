package com.campanha.rpg.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Vertice> all() {
        return (List<Vertice>) repository.findAll();
    }

    @PostMapping("/vertices")
    public Vertice newVertice(@RequestBody VerticeDTO verticeDTO) {
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
    public Vertice one(@PathVariable Long id) {

        return repository.findById(id)
        .orElseThrow(() -> new Error("Vertice not found with id: " + id));
    }

    @PutMapping("/vertices/{id}")
    public Vertice replaceVertice(@RequestBody VerticeDTO verticeDTO, @PathVariable Long id) {

        Vertice existingVertice = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vertice not found with id: " + id));


        for (String caracteristica : verticeDTO.caracteristicasInteiros.keySet()) {
            existingVertice.EditarCaracteristicaInteiros(caracteristica, verticeDTO.caracteristicasInteiros.get(caracteristica).getFirst(), verticeDTO.caracteristicasInteiros.get(caracteristica).getSecond());
        }

        for (String caracteristica : verticeDTO.caracteristicasString.keySet()) {
            existingVertice.EditarCaracteristicaString(caracteristica, verticeDTO.caracteristicasString.get(caracteristica));
        }

        Set<Long> existingVizinhosIds = new HashSet<>(existingVertice.getVizinhos());
        Set<Long> newVizinhosIds = new HashSet<>(verticeDTO.vizinhos);

        if (!existingVizinhosIds.equals(newVizinhosIds)) {
            existingVertice.clearVizinhos();
            for (Long vizinhoId : verticeDTO.vizinhos) {
                Vertice vizinho = repository.findById(vizinhoId)
                    .orElseThrow(() -> new RuntimeException("Vertice vizinho not found with id: " + vizinhoId));
                existingVertice.AdicionarVizinho(vizinho);
                vizinho.AdicionarVizinho(existingVertice);
            }
        }

        return repository.save(existingVertice);
    }

    @DeleteMapping("/vertices/{id}")
    public void deleteVertice(@PathVariable Long id) {
        repository.deleteById(id);
    }
}