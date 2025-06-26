package src.grafo.java;

import java.util.ArrayList;

public class Campanha extends Vertice {
    private int QtdItens;
    private int QtdPersonagens;
    private int QtdDungeons;
    private int QtdNPCs;
    private int QtdRegioes;
    private int QtdBaus;
    private int QtdMissoes;
    private ArrayList<Vertice> Itens;
    private ArrayList<Vertice> Personagens;
    private ArrayList<Vertice> Dungeons;
    private ArrayList<Vertice> NPCs;
    private ArrayList<Vertice> Regioes;
    private ArrayList<Vertice> Baus;
    private ArrayList<Vertice> Missoes;
    

    public Campanha(String l_nome, String l_descricao, TipoVertice l_tipo) {
        super(l_nome, l_descricao, l_tipo);
        this.QtdItens = 0;
        this.QtdPersonagens = 0;
        this.QtdDungeons = 0;
        this.QtdNPCs = 0;
        this.QtdRegioes = 0;
        this.QtdBaus = 0;
        this.QtdMissoes = 0;
        this.Itens = new ArrayList<>();
        this.Personagens = new ArrayList<>();
        this.Dungeons = new ArrayList<>();
        this.NPCs = new ArrayList<>();
        this.Regioes = new ArrayList<>();
        this.Baus = new ArrayList<>();
        this.Missoes = new ArrayList<>();
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

    public void setQtdItens(int Itens) {
        this.QtdItens = Itens;
    }
    
    public void setQtdPersonagens(int Personagens) {
        this.QtdPersonagens = Personagens;
    }

    public void setQtdDungeons(int Dungeons) {
        this.QtdDungeons = Dungeons;
    }

    public void setQtdNPCs(int NPCs) {
        this.QtdNPCs = NPCs;
    }

    public void setQtdRegioes(int Regioes) {
        this.QtdRegioes = Regioes;
    }

    public void setQtdBaus(int Baus) {
        this.QtdBaus = Baus;
    }

    public void setQtdMissoes(int Missoes) {
        this.QtdMissoes = Missoes;
    }


}
