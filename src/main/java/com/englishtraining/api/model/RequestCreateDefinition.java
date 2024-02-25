package com.englishtraining.api.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class RequestCreateDefinition {
    ObjectId id;
    WordDefinition definition;
}
