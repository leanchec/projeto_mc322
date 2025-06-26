package src.grafo.java;

public class Item extends Vertice {
    private int Atk;
    private int Def;
    private int Dur;

    public Item(String l_nome, String l_descricao, int l_Atk, int l_Def, int l_Dur) {
        super(l_nome, l_descricao, TipoVertice.ITEM);
        this.Atk = l_Atk;
        this.Def = l_Def;
        this.Dur = l_Dur;
    }

    public int getAtk() {
        return this.Atk;
    }

    public int getDef() {
        return this.Def;
    }

    public int getDur() {
        return this.Dur;
    }

    public void setAtk(int Atk) {
        this.Atk = Atk;
    }

    public void setDef(int Def) {
        this.Def = Def;
    }

    public void setDur(int Dur) {
        this.Dur = Dur;
    }
}
