package com.campanha.rpg.types;

import java.util.HashMap;
import java.util.List;

public class VerticeDTO {
    public String nome;
    public String descricao;
    public Long campanhaId;
    public Long templateId;
    public List<Long> vizinhos;
    

    public HashMap<String, String> caracteristicasString;
    public HashMap<String, Pair> caracteristicasInteiros;
}