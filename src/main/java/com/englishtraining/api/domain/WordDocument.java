package com.englishtraining.api.domain;

import com.englishtraining.api.model.RequestCreateWord;
import com.englishtraining.api.model.Word;
import com.englishtraining.api.model.WordDefinition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "words")
@NoArgsConstructor
public class WordDocument extends Word {
    @MongoId
    ObjectId id;

    public WordDocument(RequestCreateWord request){
        setName(request.getName());
        setPronunciation(request.getPronunciation());
        setCreationDate(OffsetDateTime.now());
        setLastModificationDate(OffsetDateTime.now());
        setDefinitions(new ArrayList<>());
    }

}
