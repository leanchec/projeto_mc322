package com.campanha.rpg.types;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PairConverter implements AttributeConverter<Pair, String> {
    
    @Override
    public String convertToDatabaseColumn(Pair pair) {
        if (pair == null) {
            return null;
        }
        return pair.getFirst() + ":" + pair.getSecond();
    }
    
    @Override
    public Pair convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        
        String[] parts = dbData.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format for Pair: " + dbData);
        }
        
        int first = Integer.parseInt(parts[0]);
        int second = Integer.parseInt(parts[1]);
        
        return new Pair(first, second);
    }
}