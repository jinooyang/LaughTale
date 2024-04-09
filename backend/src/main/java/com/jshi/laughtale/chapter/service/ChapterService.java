package com.jshi.laughtale.chapter.service;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.chapter.dto.ChapterFirst;
import com.jshi.laughtale.chapter.dto.ChapterLevelDto;
import com.jshi.laughtale.chapter.dto.ChapterListDto;
import com.jshi.laughtale.chapter.dto.ChapterPaginationDto;
import com.jshi.laughtale.chapter.exception.ChapterNotFoundException;
import com.jshi.laughtale.chapter.mapper.ChapterMapper;
import com.jshi.laughtale.chapter.repository.ChapterRepository;
import com.jshi.laughtale.common.dto.LevelCount;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.service.MangaService;
import com.jshi.laughtale.wordlist.service.WordListService;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final MangaService mangaService;
    private final WordListService wordListService;

    public Page<ChapterListDto.Response> getChaptersFromManga(Long mangaId, int pageNo, int size) {
        Manga manga = mangaService.findById(mangaId);
        Pageable pageable = PageRequest.of(pageNo, size);
        return chapterRepository.findAllByMangaOrderByChapterNoDesc(manga, pageable)
                .map(ChapterMapper::chapterToChapterListDto);
    }

    public ChapterFirst.Response loadFirstChapter(Long mangaId) {
        return ChapterMapper.toFirstResponse(chapterRepository.findFirstChapterByMangaId(mangaId)
                .orElseThrow(ChapterNotFoundException::new));
    }

    public Chapter loadByTitleAndChapterNo(String title, Integer chapterNo) {
        return chapterRepository.findChapterByMangaTitleAndChapterNo(title, chapterNo)
                .orElseThrow(ChapterNotFoundException::new);
    }

    public Chapter findById(Long chapterId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            return chapter.get();
        } else {
            throw new RuntimeException("Chapter not found with id: " + chapterId);
        }
    }

    public List<ChapterLevelDto.Response> getChapterLevels(Long mangaId) {

        return chapterRepository.findAllByMangaId(mangaId).stream().map(ChapterMapper::chapterToChapterLevelDto)
                .toList();

    }


    public int calculateChapterLevel(long chapterId) {
        List<Tuple> tupleList = wordListService.findCalculatedChapterLevel(chapterId);
        long totalSum = 0;
        long totalCnt = 0;
        for (Tuple tuple : tupleList) {
            int level = tuple.get("level", Integer.class);
            long cnt = tuple.get("levelcnt", Long.class);
            totalSum += level * cnt;
            totalCnt += cnt;
        }
        double avg = (double) totalSum / totalCnt;
        return averageToLevel(avg);
    }

    public int averageToLevel(double avg) {
        if (avg <= 1.67) {
            return 1;
        }
        if (avg <= 1.73) {
            return 2;
        }
        if (avg <= 1.8) {
            return 3;
        }
        if (avg <= 1.9) {
            return 4;
        }
        return 5;
    }


    //	@PostConstruct
    public void initSetLevel() {    // 기존 DB에 있지만, level이 부여 안된 chapter들 level update
        for (Chapter chapter : chapterRepository.findAll()) {
            if (chapter.getLevel() == null) {
                int chapterLevel = calculateChapterLevel(chapter.getId());
                chapter.setLevel(chapterLevel);
                chapterRepository.save(chapter);
            }
        }
    }

    public List<LevelCount.Response> getChapterLevelCount(long chapterId) {
        List<Tuple> chapterLevelList = wordListService.findCalculatedChapterLevel(chapterId);

        // 모든 레벨에 대해 count를 0으로 초기화
        List<LevelCount.Response> result = new ArrayList<>();
        for (int level = 1; level <= 5; level++) {
            result.add(LevelCount.Response.builder()
                    .level(level)
                    .count(0)
                    .build()
            );
        }

        for (Tuple tuple : chapterLevelList) {
            int level = tuple.get("level", Integer.class);
            long count = tuple.get("levelcnt", Long.class);

            result.set(level - 1, LevelCount.Response.builder()
                    .level(level)
                    .count(count)
                    .build()
            );
        }

        return result;
    }

    public ChapterPaginationDto.Response getChapterPagination(Long chapterId) {
        Chapter currChapter = chapterRepository.findById(chapterId).orElseThrow(ChapterNotFoundException::new);
        Manga manga = currChapter.getManga();
        List<Chapter> chapterList = manga.getChapter();
        Collections.sort(chapterList, (a, b) -> Long.compare(a.getChapterNo(), b.getChapterNo()));

        int cur = -1;
        for (int i = 0; i < chapterList.size(); i++) {
            if (chapterList.get(i) == currChapter) {
                cur = i;
                break;
            }
        }

        int prevIdx = Math.max(0, cur - 1);
        int nextIdx = Math.min(chapterList.size() - 1, cur + 1);

        Long prevId = prevIdx == cur ? null : chapterList.get(prevIdx).getId();
        Long nextId = nextIdx == cur ? null : chapterList.get(nextIdx).getId();

        return ChapterPaginationDto
                .Response
                .builder()
                .prevPage(prevId)
                .nextPage(nextId)
                .build();
    }

}