package src.grafo.java;

import java.util.ArrayList;
import java.util.HashMap;

public class Campanha implements Entidade{
    private String nome;
    private String descricao;
    public final TipoEntidade tEntidade;
    HashMap<String, String> Caracteristica_String;
    HashMap<String, Pair> Caracteristica_Inteiros;
    private int QtdVertices;
    private int QtdItens;
    private int QtdPersonagens;
    private int QtdDungeons;
    private int QtdNPCs;
    private int QtdRegioes;
    private int QtdBaus;
    private int QtdMissoes;
    private ArrayList<Vertice> Vertices;
    private ArrayList<Vertice> Itens;
    private ArrayList<Vertice> Personagens;
    private ArrayList<Vertice> Dungeons;
    private ArrayList<Vertice> NPCs;
    private ArrayList<Vertice> Regioes;
    private ArrayList<Vertice> Baus;
    private ArrayList<Vertice> Missoes;

    public Campanha(String l_nome, String l_descricao) {
        this.nome = l_nome;
        this.descricao = l_descricao;
        this.tEntidade = TipoEntidade.CAMPANHA;
        this.Caracteristica_String = new HashMap<>();
        this.Caracteristica_Inteiros = new HashMap<>();
        this.QtdVertices = 0;
        this.QtdItens = 0;
        this.QtdPersonagens = 0;
        this.QtdDungeons = 0;
        this.QtdNPCs = 0;
        this.QtdRegioes = 0;
        this.QtdBaus = 0;
        this.QtdMissoes = 0;
        this.Vertices = new ArrayList<>();
        this.Itens = new ArrayList<>();
        this.Personagens = new ArrayList<>();
        this.Dungeons = new ArrayList<>();
        this.NPCs = new ArrayList<>();
        this.Regioes = new ArrayList<>();
        this.Baus = new ArrayList<>();
        this.Missoes = new ArrayList<>();
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

    public int getQtdVertices() {
        return this.QtdVertices;
    }

    public int getQtdItens() {
        return this.QtdItens;
    }
    
    public int getQtdPersonagens() {
        return this.QtdPersonagens;
    }

    public int getQtdDungeons() {
        return this.QtdDungeons;
    }

    public int getQtdNPCs() {
        return this.QtdNPCs;
    }

    public int getQtdRegioes() {
        return this.QtdRegioes;
    }

    public int getQtdBaus() {
        return this.QtdBaus;
    }

    public int getQtdMissoes() {
        return this.QtdMissoes;
    }

    public ArrayList<Vertice> getVertices() {
        return this.Vertices;
    }

    public ArrayList<Vertice> getItens() {
        return this.Itens;
    }

    public ArrayList<Vertice> getPersonagens() {
        return this.Personagens;
    }

    public ArrayList<Vertice> getDungeons() {
        return this.Dungeons;
    }

    public ArrayList<Vertice> getNPCs() {
        return this.NPCs;
    }

    public ArrayList<Vertice> getRegioes() {
        return this.Regioes;
    }

    public ArrayList<Vertice> getBaus() {
        return this.Baus;
    }

    public ArrayList<Vertice> getMissoes() {
        return this.Missoes;
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

    private void setQtdVertices(int Vertcies) {
        this.QtdVertices = Vertcies;
    }

    public void setQtdItens(int Itens) {
        this.setQtdVertices(this.getQtdVertices() + (Itens - this.getQtdItens()));
        this.QtdItens = Itens;
    }
    
    public void setQtdPersonagens(int Personagens) {
        this.setQtdVertices(this.getQtdVertices() + (Personagens - this.getQtdPersonagens()));
        this.QtdPersonagens = Personagens;
    }

    public void setQtdDungeons(int Dungeons) {
        this.setQtdVertices(this.getQtdVertices() + (Dungeons - this.getQtdDungeons()));
        this.QtdDungeons = Dungeons;
    }

    public void setQtdNPCs(int NPCs) {
        this.setQtdVertices(this.getQtdVertices() + (NPCs - this.getQtdNPCs()));
        this.QtdNPCs = NPCs;
    }

    public void setQtdRegioes(int Regioes) {
        this.setQtdVertices(this.getQtdVertices() + (Regioes - this.getQtdRegioes()));
        this.QtdRegioes = Regioes;
    }

    public void setQtdBaus(int Baus) {
        this.setQtdVertices(this.getQtdVertices() + (Baus - this.getQtdBaus()));
        this.QtdBaus = Baus;
    }

    public void setQtdMissoes(int Missoes) {
        this.setQtdVertices(this.getQtdVertices() + (Missoes - this.getQtdMissoes()));
        this.QtdMissoes = Missoes;
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
    public void AdicionarVertice(Vertice novo, TipoVertice t_novo) {
        this.Vertices.add(novo);
        switch (t_novo) {
            case TipoVertice.ITEM:
                this.setQtdItens(this.getQtdItens() + 1);
                this.Itens.add(novo);
                break;
            
            case TipoVertice.PERSONAGEM:
                this.setQtdPersonagens(this.getQtdPersonagens() + 1);
                this.Personagens.add(novo);
                break;

            case TipoVertice.DUNGEON:
                this.setQtdDungeons(this.getQtdDungeons() + 1);
                this.Dungeons.add(novo);
                break;
            
            case TipoVertice.NPC:
                this.setQtdNPCs(this.getQtdNPCs() + 1);
                this.NPCs.add(novo);
                break;
            
            case TipoVertice.REGIAO:
                this.setQtdRegioes(this.getQtdRegioes() + 1);
                this.Regioes.add(novo);
                break;

            case TipoVertice.BAU:
                this.setQtdBaus(this.getQtdBaus() + 1);
                this.Baus.add(novo);
                break;

            case TipoVertice.MISSAO:
                this.setQtdMissoes(this.getQtdMissoes() + 1);
                this.Missoes.add(novo);
                break;
        }
    }

    public void AdiconarAresta(Vertice a, Vertice b) {
        a.AdicionarVizinho(b);
        b.AdicionarVizinho(a);
    }

    //TODO erro nao existe esse vertice
    public void RemoverVertice(Vertice v, TipoVertice t_v) {
        this.Vertices.remove(v);
        switch (t_v) {
            case TipoVertice.ITEM:
                this.setQtdItens(this.getQtdItens() - 1);
                this.Itens.remove(v);
                break;
            
            case TipoVertice.PERSONAGEM:
                this.setQtdPersonagens(this.getQtdPersonagens() - 1);
                this.Personagens.remove(v);
                break;

            case TipoVertice.DUNGEON:
                this.setQtdDungeons(this.getQtdDungeons() - 1);
                this.Dungeons.remove(v);
                break;
            
            case TipoVertice.NPC:
                this.setQtdNPCs(this.getQtdNPCs() - 1);
                this.NPCs.remove(v);
                break;
            
            case TipoVertice.REGIAO:
                this.setQtdRegioes(this.getQtdRegioes() - 1);
                this.Regioes.remove(v);
                break;

            case TipoVertice.BAU:
                this.setQtdBaus(this.getQtdBaus() - 1);
                this.Baus.remove(v);
                break;

            case TipoVertice.MISSAO:
                this.setQtdMissoes(this.getQtdMissoes() - 1);
                this.Missoes.remove(v);
                break;
        }
    }

    public void RemoverAresta(Vertice a, Vertice b) {
        a.RemoverVizinho(b);
        b.RemoverVizinho(a);
    }
}
