package com.campanha.rpg.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.campanha.rpg.types.Entidade;
import com.campanha.rpg.types.Pair;
import com.campanha.rpg.types.TipoEntidade;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vertices")
public class Vertice extends EntidadeBase {
    @Id
    private final Long id = 1L; // Default value, constructor should not be called directly

    private Entidade localizacao;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "campanha_id")
    private Campanha campanha;
    private String nome;
    private String descricao;
    
    private final TipoEntidade tipo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private final Template template;

    private final HashMap<String, String> Caracteristica_String = new HashMap<>();
    private final HashMap<String, Pair> Caracteristica_Inteiros = new HashMap<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "vizinhos",
        joinColumns = @JoinColumn(name = "vertice_id"),
        inverseJoinColumns = @JoinColumn(name = "vizinho_id")
    )
    private final List<Vertice> Vizinhos = new ArrayList<>();

    public Vertice(Entidade l_localizacao, Campanha l_campanha, String l_nome, String l_descricao) {
        this.localizacao = l_localizacao;
        this.campanha = l_campanha;
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tipo = TipoEntidade.VERTICE;
        this.template = null;
    }

    public Vertice(Entidade l_localizacao, Campanha l_campanha, String l_nome, String l_descricao, Template l_template) {
        this.localizacao = l_localizacao;
        this.campanha = l_campanha;
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tipo = TipoEntidade.VERTICE;
        this.template = l_template;
    }

    public Long getId() {
        return this.id;
    }

    public Entidade getLocalizacao() {
        return this.localizacao;
    }

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

    public Template getTemplate() {
        return template;
    }

    @Override
    public HashMap<String, String> getCaracteristica_String() {
        return this.Caracteristica_String;
    }

    @Override
    public HashMap<String, Pair> getCaracteristica_Inteiros() {
        return this.Caracteristica_Inteiros;
    }

    public List<Vertice> getVizinhos() {
        return Vizinhos;
    }

    public void setLocalizacao(Entidade localizacao) {
        this.localizacao = localizacao;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public void Mover(Entidade destino) {
        if (this.getLocalizacao().getTipoEntidade() == TipoEntidade.VERTICE){
            this.getCampanha().RemoverAresta(this, (Vertice) this.getLocalizacao());
        }
        if (destino.getTipoEntidade() == TipoEntidade.VERTICE) {
            this.getCampanha().AdiconarAresta(this, (Vertice) destino);
        }
        this.setLocalizacao(destino);
    }

    @Transient
    @Override
    public void Transferir(Entidade destino, Template t) {
        ArrayList<Vertice> temp = new ArrayList<>();
        for (Vertice v : this.getVizinhos()) {
            if (v.getTemplate() == t) {
                temp.add(v);
            }
        }

        for (Vertice v : temp) {
            v.Mover(destino);
        }
    }

    @Transient
    @Override
    public void Transferir(Entidade destino) {
        var temp = new ArrayList<Vertice>();
        for (Vertice v : this.getVizinhos()) {
            temp.add(v);
        }

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

    public void AdicionarVizinho(Vertice viz) {
        this.Vizinhos.add(viz);
    }

    public void RemoverVizinho(Vertice viz) {
        this.Vizinhos.remove(viz);
    }

}
