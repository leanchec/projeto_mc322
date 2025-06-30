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
import com.campanha.rpg.repositories.CampanhaRepository;
import com.campanha.rpg.repositories.TemplateRepository;
import com.campanha.rpg.types.TemplateDTO;

@RestController
public class TemplateController {

   private final TemplateRepository repository;
    private final CampanhaRepository campanhaRepository;

   public TemplateController(TemplateRepository repository, CampanhaRepository campanhaRepository) {
       this.repository = repository;
       this.campanhaRepository = campanhaRepository;
   }

    @GetMapping("/templates")
    List<Template> all() {
        return (List<Template>) repository.findAll();
    }

    @PostMapping("/templates")
    Template newTemplate(@RequestBody TemplateDTO newTemplate) {
        Campanha campanha = campanhaRepository.findById(newTemplate.campanhaId)
            .orElseThrow(() -> new RuntimeException("Campanha not found with id: " + newTemplate.campanhaId));

        Template template = new Template(campanha, newTemplate.descricao, newTemplate.nome);
        template.setCaracteristicasInteiros(newTemplate.caracteristicasInteiros);
        template.setCaracteristicasString(newTemplate.caracteristicasString);

        return repository.save(template);
    }

    // Single item

    @GetMapping("/templates/{id}")
    Template one(@PathVariable Long id) {

        return repository.findById(id)
        .orElseThrow(() -> new Error("Template not found with id: " + id));
    }

    @PutMapping("/templates/{id}")
    Template replaceTemplate(@RequestBody Template newTemplate, @PathVariable Long id) {

        return repository.findById(id)
        .map(template -> {
            template.setNome(newTemplate.getNome());
            template.setDescricao(newTemplate.getDescricao());
            return repository.save(template);
        })
        .orElseGet(() -> {
            return repository.save(newTemplate);
        });
    }

    @DeleteMapping("/templates/{id}")
    void deleteTemplate(@PathVariable Long id) {
        repository.deleteById(id);
    }
}