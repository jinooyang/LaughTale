package com.jshi.laughtale.wordbook.service;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.member.service.MemberService;
import com.jshi.laughtale.wordbook.domain.WordBook;
import com.jshi.laughtale.wordbook.dto.WordBookBasic;
import com.jshi.laughtale.wordbook.exception.AlreadyExistWordBook;
import com.jshi.laughtale.wordbook.exception.NotExistWordBookException;
import com.jshi.laughtale.wordbook.mapper.WordBookMapper;
import com.jshi.laughtale.wordbook.repository.WordBookRepository;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.worddata.service.WordDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WordBookService {

    private final WordBookRepository wordBookRepository;
    private final WordDataService wordDataService;
    private final MemberService memberService;

    public void addWord(Long wordId, Long memberId) {
        WordData wordData = wordDataService.loadWoadDataById(wordId);
        Member member = memberService.findById(memberId);
        WordBook wordBook = wordBookRepository.findByMemberAndWordData(member, wordData).orElse(null);
        if (wordBook == null) {
            wordBookRepository.save(WordBookMapper.toEntity(wordData, member));
        } else {
            throw new AlreadyExistWordBook();
        }
    }

    @Transactional(readOnly = true)
    public Page<WordBookBasic.Response> loadWordBook(int level, long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WordBook> wordBookList = wordBookRepository.findAllByMemberIdWithLevel(level, memberId, pageable);
        return wordBookList.map(WordBookMapper::toBasicResponse);
    }

    public void deleteWordBook(Long id, Long memberId) {
        WordBook wordBook = wordBookRepository.findByWordDataId(id, memberId)
                .orElseThrow(NotExistWordBookException::new);
        wordBookRepository.delete(wordBook);
    }
}
