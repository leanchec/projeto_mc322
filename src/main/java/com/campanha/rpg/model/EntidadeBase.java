package com.campanha.rpg.model;

import com.campanha.rpg.types.Entidade;
import com.campanha.rpg.types.TipoEntidade;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadeBase {
    protected String nome;
    protected String descricao;

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public abstract void Transferir(Entidade destino, Template t);
    public abstract void Transferir(Entidade destino);

    public abstract TipoEntidade getTipoEntidade();
}