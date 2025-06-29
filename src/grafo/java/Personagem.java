package src.grafo.java;

public class Personagem extends Vertice{
    
    public Personagem(Vertice l_localizacao, Campanha l_campanha, String l_nome, String l_descricao) {
        super(l_localizacao, l_campanha, l_nome, l_descricao, TipoVertice.PERSONAGEM);
    }
}
