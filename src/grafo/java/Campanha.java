package src.grafo.java;

import java.util.ArrayList;
import java.util.HashMap;

public class Campanha implements Entidade{
    private String nome;
    private String descricao;
    public final TipoEntidade tEntidade;
    HashMap<String, String> Caracteristica_String;
    HashMap<String, Pair> Caracteristica_Inteiros;
    HashMap<String, Integer> qtdTemplate;
    HashMap<String, ArrayList<Vertice>> VerticesTemplate;
    private int QtdVertices;
    private ArrayList<Vertice> Vertices;

    public Campanha(String l_nome, String l_descricao) {
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tEntidade = TipoEntidade.CAMPANHA;
        this.Caracteristica_String = new HashMap<>();
        this.Caracteristica_Inteiros = new HashMap<>();
        this.qtdTemplate = new HashMap<>();
        this.VerticesTemplate = new HashMap<>();
        this.QtdVertices = 0;
        this.Vertices = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public TipoEntidade getTipoEntidade() {
        return this.tEntidade;
    }

    public HashMap<String, String> getCaracteristica_String() {
        return this.Caracteristica_String;
    }

    public HashMap<String, Pair> getCaracteristica_Inteiros() {
        return this.Caracteristica_Inteiros;
    }

    public int getQtdTemplate(Template t) {
        return this.qtdTemplate.get(t.getNome());
    }

    public ArrayList<Vertice> getVerticesTemplate(Template t) {
        return this.VerticesTemplate.get(t.getNome());
    }

    public int getQtdVertices() {
        return this.QtdVertices;
    }

    public ArrayList<Vertice> getVertices() {
        return this.Vertices;
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

    public void RemoverCaracteristica_String(String chave) {
        this.Caracteristica_String.remove(chave);
    }

    public void RemoverCaracteristica_Inteiros(String chave) {
        this.Caracteristica_Inteiros.remove(chave);
    }

    public void Transferir(Entidade destino, Template t) {
        for (Vertice v : this.getVertices()) {
            if (v.getTemplate() == t) {
                v.Mover(destino);
            }
        }
    }

    public void Transferir(Entidade destino) {
        for(Vertice v : this.getVertices()) {
            v.Mover(destino);
        }
    }

    public void setQtdTemplate(Template t, int qtdTemplate) {
        this.qtdTemplate.replace(t.getNome(), qtdTemplate);
    }

    private void setQtdVertices(int Vertcies) {
        this.QtdVertices = Vertcies;
    }

    public Vertice Search(String nome) {
        for (Vertice v : this.getVertices()) {
            if (v.getNome() == nome) {
                return v;
            }
        }
        return null;
    }

    //TODO erro ja existe vertice com mesmo nome
    public void AdicionarVertice(Vertice novo, Template t_novo) {
        this.Vertices.add(novo);
        this.setQtdVertices(this.getQtdVertices() + 1);
        if (!this.VerticesTemplate.containsKey(t_novo.getNome())){
            this.VerticesTemplate.put(t_novo.getNome(), new ArrayList<>());
        }
        this.VerticesTemplate.get(t_novo.getNome()).add(novo);
        this.setQtdTemplate(t_novo, this.getQtdTemplate(t_novo) + 1);
    }

    public void AdicionarVertice(Vertice novo) {
        this.Vertices.add(novo);
        this.setQtdVertices(this.getQtdVertices() + 1);
    }

    public void AdiconarAresta(Vertice a, Vertice b) {
        a.AdicionarVizinho(b);
        b.AdicionarVizinho(a);
    }

    //TODO erro nao existe esse vertice
    public void RemoverVertice(Vertice v, Template t_v) {
        this.Vertices.remove(v);
        this.setQtdVertices(this.getQtdVertices() - 1);
        this.VerticesTemplate.get(t_v.getNome()).remove(v);
        this.setQtdTemplate(t_v, this.getQtdTemplate(t_v) - 1);
    }

    public void RemoverAresta(Vertice a, Vertice b) {
        a.RemoverVizinho(b);
        b.RemoverVizinho(a);
    }
}
