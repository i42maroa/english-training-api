package com.englishtraining.api.service.impl;

import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.repository.WordsRepository;
import com.englishtraining.api.service.WordsService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
}
