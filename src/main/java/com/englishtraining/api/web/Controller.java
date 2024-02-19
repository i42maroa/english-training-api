package com.englishtraining.api.web;


import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.service.WordsService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final WordsService service;

    @GetMapping("/words")
    public Mono<WordDocument> getListWord(@RequestParam ObjectId id) {
        return service.getWord(id).subscribeOn(Schedulers.boundedElastic());
    }
}
