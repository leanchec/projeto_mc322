package com.campanha.rpg.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.campanha.rpg.types.Pair;
import com.campanha.rpg.types.PairConverter;
import com.campanha.rpg.types.TipoEntidade;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Campanha extends EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L; // Default value, constructor should not be called directly

    private String nome;

    private String descricao;
        
    @Transient
    public final TipoEntidade tEntidade = TipoEntidade.CAMPANHA;
    
    @ElementCollection
    @CollectionTable(name = "campanha_caracteristicas_string", 
                    joinColumns = @JoinColumn(name = "campanha_id"))
    @MapKeyColumn(name = "chave")
    @Column(name = "valor")
    HashMap<String, String> Caracteristica_String = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "campanha_caracteristicas_inteiros", 
                    joinColumns = @JoinColumn(name = "campanha_id"))
    @MapKeyColumn(name = "chave")
    @Column(name = "valor")
    @Convert(converter = PairConverter.class)
    HashMap<String, Pair> Caracteristica_Inteiros = new HashMap<>();
    
    @OneToMany(mappedBy = "campanha", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private final ArrayList<Vertice> vertices = new ArrayList<>();

    @OneToMany(mappedBy= "campanha", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private final ArrayList<Template> templates = new ArrayList<>();

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

    @Override
    public HashMap<String, String> getCaracteristica_String() {
        return this.Caracteristica_String;
    }

    @Override
    public HashMap<String, Pair> getCaracteristica_Inteiros() {
        return this.Caracteristica_Inteiros;
    }

    public ArrayList<Template> getTemplates() {
        return templates;
    }

    public void addTemplate(Template template) {
        templates.add(template);
    }

    public int getQtdTemplate(Template t) {
        return templates.size();
    }

    public List<Vertice> getVerticesTemplate(Template t) {
        return t.getVertices();
    }

    public int getQtdVertices() {
        return vertices.size();
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
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
    public void AdicionarCaracteristica_String(String chave, String valor) {
        this.Caracteristica_String.put(chave, valor);
    }

    @Override
    public void AdicionarCaracteristica_Inteiros(String chave, int valor, int limite) {
        this.Caracteristica_Inteiros.put(chave, new Pair(valor, limite));
    }

    @Override
    public void EditarCaracteristica_String(String chave, String valor) {
        this.Caracteristica_String.replace(chave, valor);
    }

    @Override
    public void EditarCaracteristica_Inteiros(String chave, int valor, int limite) {
        this.Caracteristica_Inteiros.replace(chave, new Pair(valor, limite));
    }

    @Override
    public void RemoverCaracteristica_String(String chave) {
        this.Caracteristica_String.remove(chave);
    }

    @Override
    public void RemoverCaracteristica_Inteiros(String chave) {
        this.Caracteristica_Inteiros.remove(chave);
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
