package com.campanha.rpg.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.campanha.rpg.types.Entidade;
import com.campanha.rpg.types.Pair;
import com.campanha.rpg.types.TipoEntidade;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vertices")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vertice extends EntidadeBase {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; // Default value, constructor should not be called directly

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "campanha_id")
    private Campanha campanha;
    
    private final TipoEntidade tipo;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @jakarta.persistence.ElementCollection
    @jakarta.persistence.CollectionTable(
        name = "vertice_caracteristicas_string", 
        joinColumns = @JoinColumn(name = "vertice_id")
    )
    @jakarta.persistence.MapKeyColumn(name = "chave")
    @jakarta.persistence.Column(name = "valor")
    private Map<String, String> caracteristicasString;
    
    @jakarta.persistence.ElementCollection
    @jakarta.persistence.CollectionTable(
        name = "vertice_caracteristicas_integer", 
        joinColumns = @JoinColumn(name = "vertice_id")
    )
    @jakarta.persistence.MapKeyColumn(name = "chave")
    @jakarta.persistence.AttributeOverrides({
        @jakarta.persistence.AttributeOverride(name = "first", column = @jakarta.persistence.Column(name = "valor")),
        @jakarta.persistence.AttributeOverride(name = "second", column = @jakarta.persistence.Column(name = "limite"))
    })
    private Map<String, Pair> caracteristicasInteger;

    @ManyToMany(fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.DETACH)
    @JoinTable(
        name = "vizinhos",
        joinColumns = @JoinColumn(name = "vertice_id"),
        inverseJoinColumns = @JoinColumn(name = "vizinho_id")
    )
    @JsonBackReference("vertice-vizinhos")
    private List<Vertice> Vizinhos;

    public Vertice() { 
        this.tipo = TipoEntidade.VERTICE;
    };

    public Vertice(Campanha l_campanha, Template l_template, String l_nome, String l_descricao) {
        this.campanha = l_campanha;
        this.template = l_template;
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tipo = TipoEntidade.VERTICE;
        this.caracteristicasString = new HashMap<>();
        this.caracteristicasInteger = new HashMap<>();
        this.Vizinhos = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    @JsonIgnore
    public Campanha getCampanha() {
        return campanha;
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
        return this.tipo;
    }

    @JsonIgnore
    public Template getTemplate() {
        return template;
    }

    public Long getTemplateId() {
        return template.getId();
    }

    public Map<String, String> getCaracteristicaString() {
        return this.caracteristicasString;
    }

    public Map<String, Pair> getcaracteristicaInteiros() {
        return this.caracteristicasInteger;
    }

    public List<Long> getVizinhos() {
        if (this.Vizinhos == null) {
            return new ArrayList<>();
        }

        return this.Vizinhos.stream()
            .map(Vertice::getId)
            .toList();
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public void Mover(Entidade destino) {
        if (destino.getTipoEntidade() == TipoEntidade.VERTICE) {
            this.getCampanha().AdiconarAresta(this, (Vertice) destino);
        }
    }

    @Transient
    @Override
    public void Transferir(Entidade destino, Template t) {
        ArrayList<Vertice> temp = new ArrayList<>();
        // for (Long v : this.getVizinhos()) {
        //     if (v.getTemplate() == t) {
        //         temp.add(v);
        //     }
        // }

        for (Vertice v : temp) {
            v.Mover(destino);
        }
    }

    @Transient
    @Override
    public void Transferir(Entidade destino) {
        var temp = new ArrayList<Vertice>();
        // for (Vertice v : this.getVizinhos()) {
        //     temp.add(v);
        // }

        for (Vertice v : temp) {
            v.Mover(destino);
        }
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void AdicionarCaracteristicaString(String chave, String valor) {
        this.caracteristicasString.put(chave, valor);
    }

    public void AdicionarCaracteristicaInteiros(String chave, int valor, int limite) {
        this.caracteristicasInteger.put(chave, new Pair(valor, limite));
    }

    public void EditarCaracteristicaString(String chave, String valor) {
        this.caracteristicasString.replace(chave, valor);
    }

    public void EditarCaracteristicaInteiros(String chave, int valor, int limite) {
        this.caracteristicasInteger.replace(chave, new Pair(valor, limite));
    }

    public void RemoverCaracteristicaString(String chave) {
        this.caracteristicasString.remove(chave);
    }

    public void RemoverCaracteristicaInteiros(String chave) {
        this.caracteristicasInteger.remove(chave);
    }

    public void AdicionarVizinho(Vertice viz) {
        this.Vizinhos.add(viz);
    }

    public void RemoverVizinho(Vertice viz) {
        this.Vizinhos.remove(viz);
    }

    public void clearVizinhos() {
        for (Vertice viz : new ArrayList<>(this.Vizinhos)) {
            viz.RemoverVizinho(this);
        }

        this.Vizinhos.clear();
    }

}
