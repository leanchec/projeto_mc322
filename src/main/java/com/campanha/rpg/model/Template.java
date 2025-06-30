package com.campanha.rpg.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "templates",
    uniqueConstraints = @UniqueConstraint(columnNames = {"nome"})
)
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Template {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;
    
    @ElementCollection
    @CollectionTable(
        name = "template_caracteristicas_string",
        joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "caracteristica")
    private List<String> caracteristicasString = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "template_caracteristicas_inteiros",
        joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "caracteristica")
    private List<String> caracteristicasInteiros = new ArrayList<>();

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "campanha_id")
    @NonNull
    @JsonBackReference("campanha-vertices")
    private Campanha campanha;

    @OneToMany(mappedBy = "template", fetch = jakarta.persistence.FetchType.EAGER)
    private List<Vertice> vertices = new ArrayList<>();

    // Empty constructor for JPA
    protected Template() {}

    public Template(Template t) {
        this.nome = t.nome;
        this.descricao = t.descricao;
        this.campanha = t.campanha;
    }

    public Template(Campanha campanha, String descricao, String nome) {
        this.campanha = campanha;
        this.descricao = descricao;
        this.nome = nome;
    }

    @JsonIgnore
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
    
    public Campanha getCampanha() {
        return campanha;
    }

    public Long getCampanhaId() {
        return campanha.getId();
    }

    public List<String> getCaracteristicasString() {
        return this.caracteristicasString;
    }

    public List<String> getCaracteristicasInteiros() {
        return this.caracteristicasInteiros;
    }

    public void setCaracteristicasInteiros(List<String> caracteristicas) {
        this.caracteristicasInteiros.addAll(caracteristicas);
    }

    public void setCaracteristicasString(List<String> caracteristicas) {
        this.caracteristicasString.addAll(caracteristicas);
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void adicionarCaracteristicaString(String valor) {
        this.caracteristicasString.add(valor);
    }

    public void adicionarCaracteristicaInteiros(String caracteristica) {
        this.caracteristicasInteiros.add(caracteristica);
    }

    public void removerCaracteristicaString(String chave) {
        this.caracteristicasString.remove(chave);
    }

    public void removerCaracteristicaInteiros(String chave) {
        this.caracteristicasInteiros.remove(chave);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
