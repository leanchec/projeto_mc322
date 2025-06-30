package com.campanha.rpg.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
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


@RestController
public class CampanhaController {

   private final CampanhaRepository repository;

   public CampanhaController(CampanhaRepository repository) {
       this.repository = repository;
   }

   @GetMapping(value="/campanhasIds", produces=MediaType.APPLICATION_JSON_VALUE)
   public String getMethodName() {
        
        Collection<Campanha> campanhas = (Collection<Campanha>) repository.findAll();
        StringBuilder jsonBuilder = new StringBuilder("[");
        boolean first = true;
        for (Campanha campanha : campanhas) {
            if (!first) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("{\"id\":").append(campanha.getId())
                       .append(",\"nome\":\"").append(campanha.getNome()).append("\"}");
            first = false;
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
        
    }
   

    @GetMapping("/campanhas")
    public List<Campanha> all() {
        return (List<Campanha>) repository.findAll();
    }

    @PostMapping("/campanhas")
    public Campanha newCampanha(@RequestBody Campanha newCampanha) {
        return repository.save(newCampanha);
    }

    // Single item

    @GetMapping("/campanhas/{id}")
    public Campanha one(@PathVariable Long id) {

        return repository.findById(id)
        .orElseThrow(() -> new Error("Campanha not found with id: " + id));
    }

    @GetMapping("/campanhas/{id}/vertices")
    public List<Vertice> getVerticesInCampanha(@PathVariable Long id) {
        Optional<Campanha> campanha = repository.findById(id);

        if (campanha.isPresent()) {
            return campanha.get().getVertices();
        }

        return new ArrayList();
    }

    @GetMapping("/campanhas/{id}/templates")
    public List<Template> getTemplatesInCampanha(@PathVariable Long id) {
        Optional<Campanha> campanha = repository.findById(id);

        if (campanha.isPresent()) {
            List<Template> temp = new ArrayList<>(campanha.get().getTemplates());

            return temp;
        }

        return new ArrayList();
    }

    @PutMapping("/campanhas/{id}")
    public Campanha replaceCampanha(@RequestBody Campanha newCampanha, @PathVariable Long id) {

        return repository.findById(id)
        .map(Campanha -> {
            Campanha.setNome(newCampanha.getNome());
            Campanha.setDescricao(newCampanha.getDescricao());
            return repository.save(Campanha);
        })
        .orElseGet(() -> {
            return repository.save(newCampanha);
        });
    }

    @DeleteMapping("/campanhas/{id}")
    public void deleteCampanha(@PathVariable Long id) {
        repository.deleteById(id);
    }
}