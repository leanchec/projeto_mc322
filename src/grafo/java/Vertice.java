package src.grafo.java;

import java.util.ArrayList;
import java.util.HashMap;

public class Vertice implements Entidade {
    private Entidade localizacao;
    private Campanha campanha;
    private String nome;
    private String descricao;
    private final TipoEntidade tipo;
    private final Template template;
    private final HashMap<String, String> Caracteristica_String;
    private final HashMap<String, Pair> Caracteristica_Inteiros;
    private final ArrayList<Vertice> Vizinhos;

    public Vertice(Entidade l_localizacao, Campanha l_campanha, String l_nome, String l_descricao) {
        this.localizacao = l_localizacao;
        this.campanha = l_campanha;
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tipo = TipoEntidade.VERTICE;
        this.template = null;
        Caracteristica_String = new HashMap<>();
        Caracteristica_Inteiros = new HashMap<>();
        Vizinhos = new ArrayList<>();
    }

    public Vertice(Entidade l_localizacao, Campanha l_campanha, String l_nome, String l_descricao, Template l_template) {
        this.localizacao = l_localizacao;
        this.campanha = l_campanha;
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tipo = TipoEntidade.VERTICE;
        this.template = l_template;
        Caracteristica_String = new HashMap<>(l_template.getCaracteristica_String());
        Caracteristica_Inteiros = new HashMap<>(l_template.getCaracteristica_Inteiros());
        Vizinhos = new ArrayList<>();
    }

    public Entidade getLocalizacao() {
        return this.localizacao;
    }

    public Campanha getCampanha() {
        return this.campanha;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public TipoEntidade getTipoEntidade() {
        return this.tipo;
    }

    public Template getTemplate() {
        return this.template;
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

    public void Transferir(Entidade destino) {
        ArrayList<Vertice> temp = new ArrayList<>();
        for (Vertice v : this.getVizinhos()) {
            temp.add(v);
        }

        for (Vertice v : temp) {
            v.Mover(destino);
        }
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

    public void AdicionarVizinho(Vertice viz) {
        this.Vizinhos.add(viz);
    }

    public void RemoverVizinho(Vertice viz) {
        this.Vizinhos.remove(viz);
    }
}
