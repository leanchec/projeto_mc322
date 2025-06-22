package src.grafo.java;

import java.util.ArrayList;

public class Ambiente {
    private int tamanho;
    private final ArrayList<Campanha> Campanhas;

    public Ambiente() {
        this.tamanho = 0;
        this.Campanhas = new ArrayList<>();
    }

    public int getTamanho() {
        return this.tamanho;
    }

    private void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void criar_campanha(String nome, String descricao) {
        final Campanha c = new Campanha(nome, descricao, TipoVertice.CAMPANHA);
        this.setTamanho(this.getTamanho()+1);
        this.Campanhas.add(c);
    }

    public ArrayList<Campanha> getCampanhas() {
        return this.Campanhas;
    }


    //TODO erro de nao ter campanha
    public Campanha Search(String nome) {
        for (Campanha c : this.getCampanhas()) {
            if (c.getNome() ==  nome){
                return c;
            }
        }
    }

    //TODO erro id nao existente
    public Campanha Search(int id) {
        return this.getCampanhas().get(id);
    }
}
