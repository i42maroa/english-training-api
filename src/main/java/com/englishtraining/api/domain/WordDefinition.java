package com.englishtraining.api.domain;


import com.englishtraining.api.domain.enums.WordType;
import lombok.Data;

import java.util.List;

@Data
public class WordDefinition {
    WordType type;
    String translation;
    String explanation;
    List<WordExample> examples;
}

