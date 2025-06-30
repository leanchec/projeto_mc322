package com.campanha.rpg.model;
import java.util.ArrayList;
import java.util.List;

import com.campanha.rpg.types.Entidade;
import com.campanha.rpg.types.TipoEntidade;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Campanha extends EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L; // Default value, constructor should not be called directly

    @Transient
    public final TipoEntidade tEntidade = TipoEntidade.CAMPANHA;
    
    @OneToMany(mappedBy = "campanha", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("campanha-vertices")
    public List<Vertice> vertices = new ArrayList<>();

    @OneToMany(mappedBy = "campanha", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("campanha-templates")
    public List<Template> templates = new ArrayList<>();

    protected Campanha() {
        // Default constructor for JPA
    }

    public Campanha(String l_nome, String l_descricao) {
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.id = 0L; // Default value, constructor should not be called directly
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public TipoEntidade getTipoEntidade() {
        return this.tEntidade;
    }

    public List<Template> getTemplates() {
        for(Template t : templates) {
            System.out.println(t.getId() + " - Template: " + t.getNome() + " - " + t.getDescricao());
        }
        return templates;
    }

    public void addTemplate(Template template) {
        templates.add(template);
    }

    public int getQtdTemplate(Template t) {
        return templates.size();
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Vertice> getVerticesTemplate(Template t) {
        return t.getVertices();
    }

    public int getQtdVertices() {
        return vertices.size();
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    @Transient
    public void Transferir(Entidade destino, Template t) {
        for (Vertice v : new ArrayList<>(this.getVertices())) {
            if (v.getTemplate() == t) {
                v.Mover(destino);
            }
        }
    }

    @Override
    @Transient
    public void Transferir(Entidade destino) {
        for(Vertice v : new ArrayList<>(this.getVertices())) {
            v.Mover(destino);
        }
    }

    public Vertice Search(String nome) {
        return this.getVertices().stream()
                .filter(v -> v.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    public void AdicionarVertice(Vertice novo, Template t_novo) {
        this.vertices.add(novo);
        novo.setCampanha(this);
    }

    public void AdicionarVertice(Vertice novo) {
        this.vertices.add(novo);
        novo.setCampanha(this);
    }

    public void AdiconarAresta(Vertice a, Vertice b) {
        a.AdicionarVizinho(b);
        b.AdicionarVizinho(a);
    }

    public void RemoverVertice(Vertice v, Template t_v) {
        this.vertices.remove(v);
    }

    public void RemoverVertice(Vertice v) {
        this.vertices.remove(v);
    }

    public void RemoverAresta(Vertice a, Vertice b) {
        a.RemoverVizinho(b);
        b.RemoverVizinho(a);
    }
}
