package com.englishtraining.api.service;

import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.model.*;
import com.englishtraining.api.model.enums.WordType;
import com.englishtraining.api.model.input.WordPageInputQuery;
import com.englishtraining.api.model.output.Pagination;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface WordsService {

    Mono<WordDocument> getWord(ObjectId id);

    Mono<Pagination<WordDocument>> getWords(WordPageInputQuery query, Pageable pageRequest);


    Mono<WordDocument> insertWord(RequestCreateWord word);

    Mono<Void> deleteWord(ObjectId id);

    Mono<UpdateResult> insertDefinition(RequestCreateDefinition definition);

    Mono<UpdateResult> deleteDefinition(ObjectId id, WordType type, String translation);

    Mono<UpdateResult> insertExample(RequestCreateExample example);
    Mono<BulkWriteResult> deleteExample(ObjectId id, WordType type, String translation, Integer pos);
}
