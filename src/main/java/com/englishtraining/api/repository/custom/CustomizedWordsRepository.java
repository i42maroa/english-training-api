package com.englishtraining.api.repository.custom;

import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.model.WordDefinition;
import com.englishtraining.api.model.WordExample;
import com.englishtraining.api.model.enums.WordType;
import com.englishtraining.api.model.input.WordPageInputQuery;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomizedWordsRepository extends CustomizedBaseRepository{

    Flux<WordDocument> findWordsPage(WordPageInputQuery filters, Pageable pageable);

    Mono<Long> countWords(WordPageInputQuery filters);


    Mono<UpdateResult> insertDefinition(ObjectId id, WordDefinition definition);

    Mono<UpdateResult> insertExample(ObjectId id, WordType type, String translation, WordExample example);

    Mono<UpdateResult> pullDefinition(ObjectId id, WordType type, String translation);

    Mono<BulkWriteResult> pullExampleByArrayPosition(ObjectId id, WordType type, String translation, Integer pos);

    default Bson idFilter(ObjectId id) {
        return Filters.eq("_id", id);
    }
}
