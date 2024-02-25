package com.englishtraining.api.service.impl;

import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.model.RequestCreateDefinition;
import com.englishtraining.api.model.RequestCreateExample;
import com.englishtraining.api.model.RequestCreateWord;
import com.englishtraining.api.model.enums.WordType;
import com.englishtraining.api.model.input.WordPageInputQuery;
import com.englishtraining.api.model.output.Pagination;
import com.englishtraining.api.repository.WordsRepository;
import com.englishtraining.api.service.WordsService;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordsService {

    private final WordsRepository wordsRepository;

    @Override
    public Mono<WordDocument> getWord(ObjectId id) {
        return wordsRepository.findById(id);
    }

    @Override
    public Mono<Pagination<WordDocument>> getWords(WordPageInputQuery query, Pageable pageRequest) {
        var listWordsMono = wordsRepository.findWordsPage(query, pageRequest).collectList();
        var countMono = wordsRepository.countWords(query);

        return Mono.zip(listWordsMono, countMono, (items, count) -> new Pagination<>(items, pageRequest, count));
    }

    @Override
    public Mono<WordDocument> insertWord(RequestCreateWord request) {
        var word = new WordDocument(request);
        return wordsRepository.insert(word);
    }

    @Override
    public Mono<Void> deleteWord(ObjectId id) {
        return wordsRepository.deleteById(id);
    }

    @Override
    public Mono<UpdateResult> insertDefinition(RequestCreateDefinition definition) {
        return wordsRepository.insertDefinition(definition.getId(), definition.getDefinition());
    }

    @Override
    public Mono<UpdateResult> deleteDefinition(ObjectId id, WordType type, String translation) {
        return wordsRepository.pullDefinition(id, type, translation);
    }

    @Override
    public Mono<UpdateResult> insertExample(RequestCreateExample example) {
        return wordsRepository.insertExample(example.getId(), example.getType(), example.getTranslation(), example.getExample());
    }

    @Override
    public Mono<BulkWriteResult> deleteExample(ObjectId id, WordType type, String translation, Integer pos) {
        return wordsRepository.pullExampleByArrayPosition(id, type, translation, pos);
    }
}
