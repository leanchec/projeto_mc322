package src.grafo.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Vertice {
    private String nome;
    private String descricao;
    public final TipoVertice tipo;
    Map<String, String> Caracteristica_String;
    Map<String, Pair> Caracteristica_Inteiros;
    private final ArrayList<Vertice> Vizinhos;

    public Vertice(String l_nome, String l_descricao, TipoVertice l_tipo) {
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tipo = l_tipo;
        Caracteristica_String = new HashMap<>();
        Caracteristica_Inteiros = new HashMap<>();
        Vizinhos = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public HashMap<String, String> getCaracteristica_String() {
        return this.getCaracteristica_String();
    }

    public HashMap<String, Pair> getCaracteristica_Inteiros() {
        return this.getCaracteristica_Inteiros();
    }

    public ArrayList<Vertice> getVizinhos() {
        return this.Vizinhos;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void RemoverCaracteristica_String(String chave, String valor) {
        this.Caracteristica_String.remove(chave);
    }

    public void RemoverCaracteristica_Inteiros(String chave, int valor, int limite) {
        this.Caracteristica_Inteiros.remove(chave);
    }

    public void AdicionarVizinho(Vertice viz) {
        this.Vizinhos.add(viz);
    }

    public void RemoverVizinho(Vertice viz) {
        this.Vizinhos.remove(viz);
    }
}
