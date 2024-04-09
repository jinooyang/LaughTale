package com.jshi.laughtale.wordhistory.service;

import com.jshi.laughtale.member.service.MemberService;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.wordhistory.domain.WordHistory;
import com.jshi.laughtale.wordhistory.dto.WordHistoryDto;
import com.jshi.laughtale.wordhistory.mapper.WordHistoryMapper;
import com.jshi.laughtale.wordhistory.repository.WordHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordHistoryService {
    private final WordHistoryRepository wordHistoryRepository;
    private final MemberService memberService;

    public WordHistoryDto.Response getMemberWordHistoryData(Long memberId) {
        return WordHistoryDto.Response.builder()
                .memberId(memberId)
                .historyList(getMemberWordHistoryWhereStudyCntGreaterThanZero(memberId).stream().map(WordHistoryMapper::toMemberWordData).toList())
                .build();
    }

    public List<WordHistory> getMemberWordHistoryWhereStudyCntGreaterThanZero(Long memberId) {
        return wordHistoryRepository.findByMemberIdAndStudyCntGreaterThan(memberId, 0);

    }


    public List<WordHistory> getMemberWordHistory(Long memberId) {

        return wordHistoryRepository.findByMemberId(memberId);
    }

    public List<WordHistory> findByWordIdIn(List<Long> list) {
        log.info(list.toString());
        return wordHistoryRepository.findAllByWordDataIdIn(list);
    }

    public void addWordHistory(Long memberId, WordData wordData, int point) {
        Optional<WordHistory> wordHistory =
                wordHistoryRepository.findByMemberIdAndWordDataId(memberId, wordData.getId());
        //이미 학습한 적이 있다면
        if (wordHistory.isPresent()) {
            WordHistory history = wordHistory.get();
            //학습 횟수를 맞았으면 증가시키고 틀렸으면 증가시키지 않는다
            history.setStudyCnt(history.getStudyCnt() + point);
            //틀렸다면 OFFSET을 10 증가시키고 맞았으면 0으로 초기화시킨다
            history.setOffset((history.getOffset() + 10) * Math.abs(1 - point));
            history.setStudyDate(LocalDateTime.now());
            wordHistoryRepository.save(history);
        }
        //최초 학습한 단어라면
        else {
            wordHistoryRepository.save(
                    WordHistory.builder()
                            .studyCnt(point)
                            .offset(10 * (1 - point))
                            .studyDate(LocalDateTime.now())
                            .member(memberService.findById(memberId))
                            .wordData(wordData)
                            .build()
            );
        }
    }
}
