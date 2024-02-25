package com.englishtraining.api.repository.custom.impl;

import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.model.WordDefinition;
import com.englishtraining.api.model.WordExample;
import com.englishtraining.api.model.enums.WordType;
import com.englishtraining.api.model.input.WordPageInputQuery;
import com.englishtraining.api.repository.custom.CustomizedWordsRepository;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.englishtraining.api.constant.WordDocumentConstants.*;

@RequiredArgsConstructor
public class CustomizedWordsRepositoryImpl implements CustomizedWordsRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<WordDocument> findWordsPage(WordPageInputQuery filters, Pageable pageable) {
        var criteria = getAllCriteria(filters, this::getWordsPageCriterionList);
        var query = new Query()
                .with(pageable)
                .addCriteria(criteria);

        return reactiveMongoTemplate.find(query, WordDocument.class);
    }

    @Override
    public Mono<Long> countWords(WordPageInputQuery filters) {
        var criteria = getAllCriteria(filters, this::getWordsPageCriterionList);
        var query = new Query().addCriteria(criteria);
        return reactiveMongoTemplate.count(query, WordDocument.class);
    }

    @Override
    public Mono<UpdateResult> insertDefinition(ObjectId id, WordDefinition definition) {

        var update = new Document()
                .append(WORDS_DEF_TYPE, definition.getType())
                .append(WORDS_DEF_TRANSLATION, definition.getTranslation())
                .append(WORDS_DEF_EXPLANATION, definition.getExplanation());
        var pushUpdate = Updates.addToSet(WORDS_DEFINITIONS, update);


        return reactiveMongoTemplate
                .getCollection(WORDS_COLLECTION)
                .flatMap(collection -> Mono.from(collection.updateOne(idFilter(id), pushUpdate)));
    }

    @Override
    public Mono<UpdateResult> insertExample(ObjectId id, WordType type, String translation, WordExample example) {

        var update = new Document()
                .append(WORDS_DEF_TRANS_EXAMPLE_EXPLANATION, example.getExplanation())
                .append(WORDS_DEF_TRANS_EXAMPLE_PHRASE, example.getPhrase())
                .append(WORDS_DEF_TRANS_EXAMPLE_TRANSLATION, example.getTranslation());

        var arrayFilter = new Document("param.type", type).append("param.translation", translation);

        var pushUpdate = Updates.addToSet(WORDS_DEF_TRANSLATION_EXAMPLES_ARRAY, update);

        return reactiveMongoTemplate
                .getCollection(WORDS_COLLECTION)
                .flatMap(collection -> Mono.from(collection.updateOne(idFilter(id), pushUpdate, new UpdateOptions().arrayFilters(Collections.singletonList(arrayFilter)))));
    }

    private List<Criteria> getWordsPageCriterionList(WordPageInputQuery filters){
        return Stream.of(
                equalsCriteria("name", filters.getName())
//                equalsCriteria("translation", filters.getTranslation()),
//                equalsCriteria("type", filters.getType())
        )
                .flatMap(Function.identity())
                .toList();
    }
}
