package com.englishtraining.api.service;

import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.model.RequestCreateDefinition;
import com.englishtraining.api.model.RequestCreateExample;
import com.englishtraining.api.model.RequestCreateWord;
import com.englishtraining.api.model.Word;
import com.englishtraining.api.model.input.WordPageInputQuery;
import com.englishtraining.api.model.output.Pagination;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface WordsService {

    Mono<WordDocument> getWord(ObjectId id);

    Mono<Pagination<WordDocument>> getWords(WordPageInputQuery query, Pageable pageRequest);

    Mono<WordDocument> insertWord(RequestCreateWord word);

    Mono<UpdateResult> insertDefinition(RequestCreateDefinition definition);

    Mono<UpdateResult> insertExample(RequestCreateExample example);
}
