package com.englishtraining.api.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Document(collection = "words")
public class WordDocument {
    @MongoId
    ObjectId id;
    //OffsetDateTime date;
    //OffsetDateTime lastModificationDate;
    String name;
    String pronunciation;
    List<WordDefinition> definitions;
}
