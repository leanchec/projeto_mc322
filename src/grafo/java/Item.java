package src.grafo.java;

public class Item extends Vertice {
    private Vertice localizacao;
    private Campanha campanha;

    public Item(String l_nome, String l_descricao, Vertice l_localizacao, Campanha l_campanha) {
        super(l_nome, l_descricao, TipoVertice.ITEM);
        this.localizacao = l_localizacao;
        this.campanha = l_campanha;
    }

    public Vertice getLocalizacao() {
        return this.localizacao;
    }

    public Campanha getCampanha() {
        return this.campanha;
    }

    public void setLocalizacao(Vertice localizacao) {
        this.localizacao = localizacao;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public void Mover(Vertice destino) {
        this.getCampanha().RemoverAresta(this, this.getLocalizacao());
        this.getCampanha().AdiconarAresta(this, destino);
        this.setLocalizacao(destino);
    }
}
