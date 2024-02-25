package com.englishtraining.api.model;

import com.englishtraining.api.model.enums.WordType;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class RequestCreateExample {
    ObjectId id;
    WordType type;
    String translation;
    WordExample example;
}
