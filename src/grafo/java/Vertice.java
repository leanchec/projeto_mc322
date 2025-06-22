package src.grafo.java;

import java.util.ArrayList;

public abstract class Vertice {
    private String nome;
    private String descricao;
    public final TipoVertice tipo;
    private final ArrayList<Vertice> Vizinhos;

    public Vertice(String l_nome, String l_descricao, TipoVertice l_tipo) {
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tipo = l_tipo;
        Vizinhos = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
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

    public void AdicionarVizinho(Vertice viz) {
        this.Vizinhos.add(viz);
    }
}
