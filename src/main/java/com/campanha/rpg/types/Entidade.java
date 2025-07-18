package com.campanha.rpg.types;

import java.util.HashMap;

import com.campanha.rpg.model.Template;

public interface Entidade {

    public String getNome();
    public String getDescricao();
    public HashMap<String, String> getCaracteristica_String();
    public HashMap<String, Pair> getCaracteristica_Inteiros();

    public void setNome(String nome);
    public void setDescricao(String descricao);
    public void AdicionarCaracteristica_String(String chave, String valor);
    public void AdicionarCaracteristica_Inteiros(String chave, int valor, int limite);
    public void EditarCaracteristica_String(String chave, String valor);
    public void EditarCaracteristica_Inteiros(String chave, int valor, int limite);
    public void RemoverCaracteristica_String(String chave);
    public void RemoverCaracteristica_Inteiros(String chave);
    public void Transferir(Entidade destino, Template t);
    public void Transferir(Entidade destino);

    public TipoEntidade getTipoEntidade();
}
