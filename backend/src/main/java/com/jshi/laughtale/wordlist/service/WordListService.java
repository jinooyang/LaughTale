package com.jshi.laughtale.wordlist.service;

import com.jshi.laughtale.quiz.dto.QuizWord;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.wordlist.domain.WordList;
import com.jshi.laughtale.wordlist.repository.WordListRepository;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WordListService {

    private final WordListRepository wordListRepository;

    @Transactional(readOnly = true)
    public List<QuizWord> findWordListsWithLevel(int level, long chapterId) {
        List<Tuple> tempList = wordListRepository.findWordListsWithLevel(level, chapterId);
        List<QuizWord> quizList = new ArrayList<>();
        for (Tuple tuple : tempList) {
            quizList.add(QuizWord.builder()
                    .wordDataId(tuple.get("word_data_id", Long.class))
                    .answerWord(tuple.get("answer_word", String.class))
                    .level(tuple.get("level", Integer.class))
                    .wordListId(tuple.get("word_list_id", Long.class))
                    .speechId(tuple.get("speech_id", Long.class))
                    .sentence(tuple.get("sentence", String.class))
                    .definition(tuple.get("definition", String.class))
                    .height(tuple.get("height", Integer.class))
                    .width(tuple.get("width", Integer.class))
                    .build());
        }
        return quizList;
    }

    @Transactional(readOnly = true)
    public WordList findByWordDataIdAndSpeechId(Long wordId, Long speechId) {
        return wordListRepository.findByWordDataIdAndSpeechId(wordId, speechId).get(0);
    }

    @Transactional(readOnly = true)
    public List<WordList> loadWordListByWordData(WordData wordData) {
        return wordListRepository.findAllByWordData(wordData);
    }

    @Transactional(readOnly = true)
    public List<WordList> loadWordListBySpeechId(Long speechId) {
        return wordListRepository.findAllBySpeechId(speechId);
    }

    public List<Tuple> findCalculatedChapterLevel(Long chapterId) {
        return wordListRepository.findCalculatedChapterLevel(chapterId);
    }

    public List<Tuple> findCalculatedMangaLevel(Long mangaId) {
        return wordListRepository.findCalculatedMangaLevel(mangaId);
    }
}
