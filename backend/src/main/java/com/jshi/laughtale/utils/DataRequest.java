package com.jshi.laughtale.utils;

import com.jshi.laughtale.manga.dto.MangaAnalyze;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class DataRequest {
    private static final String QUERY_PREFIX = "query";
    private static final String FROM_LANG = "from_lang";
    private static final String TO_LANG = "to_lang";
    private static final String FROM = "ja";
    private static final String TO = "ko";
    private static final String BASE_URL = "http://manga-python:8000";
    private static final WebClient webClient = WebClient.create(BASE_URL);

    public static Map analyze(MangaAnalyze.Request manga) {
        return webClient
                .post()
                .uri("/api/extract")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(manga))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public static String translate(String word) {
        Map result = webClient
                .get()
                .uri("/api/trans", uriBuilder -> uriBuilder
                        .queryParam(QUERY_PREFIX, word)
                        .queryParam(FROM_LANG, FROM)
                        .queryParam(TO_LANG, TO)
                        .build()
                ).retrieve()
                .bodyToMono(Map.class)
                .block();
        return (String) result.get("result");
    }
}
