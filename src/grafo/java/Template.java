package src.grafo.java;

import java.util.HashMap;

public class Template {
    private final String nome;
    private String descricao;
    private HashMap<String, String> Caracteristica_String;
    private HashMap<String, Pair> Caracteristica_Inteiros;

    public Template(String l_nome, String l_descricao) {
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.Caracteristica_String = new HashMap<>();
        this.Caracteristica_Inteiros = new HashMap<>();
    }

    public Template(Template t) {
        this.nome = t.nome;
        this.descricao = t.descricao;
        this.Caracteristica_String = new HashMap<>(t.Caracteristica_String);
        this.Caracteristica_Inteiros = new HashMap<>(t.Caracteristica_Inteiros);
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
