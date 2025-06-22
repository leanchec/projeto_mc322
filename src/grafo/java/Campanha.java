package src.grafo.java;

public class Campanha extends Vertice {
    private int Itens;
    private int Personagens;
    private int Dungeons;
    private int NPCs;
    private int Regioes;
    private int Baus;
    private int Missoes;

    public Campanha(String l_nome, String l_descricao, TipoVertice l_tipo) {
        super(l_nome, l_descricao, l_tipo);
        this.Itens = 0;
        this.Personagens = 0;
        this.Dungeons = 0;
        this.NPCs = 0;
        this.Regioes = 0;
        this.Baus = 0;
        this.Missoes = 0;
    }

    public int getItens() {
        return this.Itens;
    }
    
    public int getPersonagens() {
        return this.Personagens;
    }

    public int getDungeons() {
        return this.Dungeons;
    }

    public int getNPCs() {
        return this.NPCs;
    }

    public int getRegioes() {
        return this.Regioes;
    }

    public int getBaus() {
        return this.Baus;
    }

    public int getMissoes() {
        return this.Missoes;
    }

    public void setItens(int Itens) {
        this.Itens = Itens;
    }
    
    public void setPersonagens(int Personagens) {
        this.Personagens = Personagens;
    }

    public void setDungeons(int Dungeons) {
        this.Dungeons = Dungeons;
    }

    public void setNPCs(int NPCs) {
        this.NPCs = NPCs;
    }

    public void setRegioes(int Regioes) {
        this.Regioes = Regioes;
    }

    public void setBaus(int Baus) {
        this.Baus = Baus;
    }

    public void setMissoes(int Missoes) {
        this.Missoes = Missoes;
    }
}
