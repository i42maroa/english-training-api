package com.englishtraining.api.service;

import com.englishtraining.api.domain.WordDocument;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface WordsService {

    Mono<WordDocument> getWord(ObjectId id);
}
