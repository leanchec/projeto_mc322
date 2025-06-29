package com.campanha.rpg.model;

import java.util.HashMap;

import com.campanha.rpg.types.Entidade;
import com.campanha.rpg.types.Pair;
import com.campanha.rpg.types.TipoEntidade;

public abstract class EntidadeBase {

    public abstract String getNome();
    public abstract String getDescricao();
    public abstract HashMap<String, String> getCaracteristica_String();
    public abstract HashMap<String, Pair> getCaracteristica_Inteiros();

    public abstract void setNome(String nome);
    public abstract void setDescricao(String descricao);
    public abstract void AdicionarCaracteristica_String(String chave, String valor);
    public abstract void AdicionarCaracteristica_Inteiros(String chave, int valor, int limite);
    public abstract void EditarCaracteristica_String(String chave, String valor);
    public abstract void EditarCaracteristica_Inteiros(String chave, int valor, int limite);
    public abstract void RemoverCaracteristica_String(String chave);
    public abstract void RemoverCaracteristica_Inteiros(String chave);
    public abstract void Transferir(Entidade destino, Template t);
    public abstract void Transferir(Entidade destino);

    public abstract TipoEntidade getTipoEntidade();
}