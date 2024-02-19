package com.englishtraining.api.repository;

import com.englishtraining.api.domain.WordDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WordsRepository extends ReactiveMongoRepository<WordDocument, ObjectId> {
}
