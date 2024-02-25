package com.englishtraining.api.web;


import com.englishtraining.api.domain.WordDocument;
import com.englishtraining.api.model.RequestCreateDefinition;
import com.englishtraining.api.model.RequestCreateExample;
import com.englishtraining.api.model.RequestCreateWord;
import com.englishtraining.api.model.enums.WordType;
import com.englishtraining.api.model.input.WordPageInputQuery;
import com.englishtraining.api.model.output.Pagination;
import com.englishtraining.api.service.WordsService;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final WordsService service;

    @GetMapping("/word")
    public Mono<WordDocument> getWord(@RequestParam ObjectId id) {
        return service.getWord(id).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/words")
    public Mono<Pagination<WordDocument>> getWords(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) WordType type,
            @RequestParam(required = false) String translation,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        var wordQuery = new WordPageInputQuery(name, type, translation);
        var pageRequest = PageRequest.of(page, size);
        return service.getWords(wordQuery, pageRequest).subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping("/word")
    public Mono<WordDocument> insertWord(@RequestBody RequestCreateWord request) {
        return service.insertWord(request);
    }

    @PostMapping("/definition")
    public Mono<UpdateResult> insertDefinition(@RequestBody RequestCreateDefinition request) {
        return service.insertDefinition(request);
    }

    @PostMapping("/example")
    public Mono<UpdateResult> insertExample(@RequestBody RequestCreateExample request) {
        return service.insertExample(request);
    }

}
