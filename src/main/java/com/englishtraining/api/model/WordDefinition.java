package com.englishtraining.api.model;


import com.englishtraining.api.model.enums.WordType;
import lombok.Data;

import java.util.List;

@Data
public class WordDefinition {
    WordType type;
    String translation;
    String explanation;
    List<WordExample> examples;
}

