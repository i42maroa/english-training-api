package com.englishtraining.api.model.input;

import com.englishtraining.api.model.enums.WordType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WordPageInputQuery {
    private String name;
    private WordType type;
    private String translation;
}
