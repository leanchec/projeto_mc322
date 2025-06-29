package com.campanha.rpg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.campanha.rpg.types.Pair;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final Long id = 1L;

    private final String nome;
    private String descricao;
    private final HashMap<String, String> Caracteristica_String = new HashMap<>();
    private final HashMap<String, Pair> Caracteristica_Inteiros = new HashMap<>();

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "campanha_id")
    private final Campanha campanha;

    @OneToMany(mappedBy = "template", fetch = jakarta.persistence.FetchType.LAZY)
    private final List<Vertice> vertices = new ArrayList<>();

    public Template(String l_nome, String l_descricao) {
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.campanha = null; // Default value, constructor should not be called directly
    }

    public Template(Template t) {
        this.nome = t.nome;
        this.descricao = t.descricao;
        this.campanha = t.campanha;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public HashMap<String, String> getCaracteristica_String() {
        return this.Caracteristica_String;
    }

    public HashMap<String, Pair> getCaracteristica_Inteiros() {
        return this.Caracteristica_Inteiros;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void AdicionarCaracteristica_String(String chave, String valor) {
        this.Caracteristica_String.put(chave, valor);
    }

    public void AdicionarCaracteristica_Inteiros(String chave, int valor, int limite) {
        this.Caracteristica_Inteiros.put(chave, new Pair(valor, limite));
    }

    public void EditarCaracteristica_String(String chave, String valor) {
        this.Caracteristica_String.replace(chave, valor);
    }

    public void EditarCaracteristica_Inteiros(String chave, int valor, int limite) {
        this.Caracteristica_Inteiros.replace(chave, new Pair(valor, limite));
    }

    public void RemoverCaracteristica_String(String chave) {
        this.Caracteristica_String.remove(chave);
    }

    public void RemoverCaracteristica_Inteiros(String chave) {
        this.Caracteristica_Inteiros.remove(chave);
    }
}
