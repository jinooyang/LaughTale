package com.jshi.laughtale.manga.service;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.chapter.repository.ChapterRepository;
import com.jshi.laughtale.common.dto.LevelCount;
import com.jshi.laughtale.manga.component.MangaAnalyzer;
import com.jshi.laughtale.manga.component.MangaSaver;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.dto.LevelManga;
import com.jshi.laughtale.manga.dto.MangaAnalyze;
import com.jshi.laughtale.manga.dto.MangaUpload;
import com.jshi.laughtale.manga.dto.RecentManga;
import com.jshi.laughtale.manga.mapper.MangaMapper;
import com.jshi.laughtale.manga.repository.MangaRepository;
import com.jshi.laughtale.member.service.MemberService;
import com.jshi.laughtale.parser.Attribute;
import com.jshi.laughtale.parser.context.MangaContext;
import com.jshi.laughtale.parser.service.ParseService;
import com.jshi.laughtale.utils.DataRequest;
import com.jshi.laughtale.utils.FileUtils;
import com.jshi.laughtale.wordlist.repository.WordListRepository;
import com.jshi.laughtale.wordlist.service.WordListService;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MangaService {

    //Repository
    private final MangaRepository mangaRepository;
    private final ChapterRepository chapterRepository;
    private final WordListRepository wordListRepository;

    //Component
    private final MangaAnalyzer mangaAnalyzer;
    private final MangaSaver mangaSaver;

    //Service
    private final ParseService parseService;
    private final MemberService memberService;
    private final WordListService wordListService;

    /**
     * 파일 저장, 사진 분석 요청, 파싱, 저장 or 파일 삭제
     */
    public MangaAnalyze.Response upload(MultipartFile thumbnail, MangaUpload.Request manga, List<MultipartFile> files)
            throws IOException {
        log.info("파일 저장...");
        String thumbnailPath = FileUtils.save(thumbnail, manga.getTitle(), Attribute.THUMBNAIL.toString());
        int last = mangaRepository.findLastChapterByManga(manga.getTitle()).orElse(0) + 1;
        List<String> names = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = FileUtils.save(file, manga.getTitle(), String.valueOf(last));
            names.add(filename);
        }

        MangaAnalyze.Request analyzeRequest = MangaMapper.toAnalyze(thumbnailPath, manga, last, names);
        Map result = DataRequest.analyze(analyzeRequest);

        log.info("파싱...");
        MangaContext mangaContext = parseService.parse(result);
        mangaContext.setManga(mangaRepository.findByTitle(manga.getTitle()).orElse(mangaContext.getManga()));

        log.info("분석...");
        MangaAnalyze.Response response = mangaAnalyzer.analyze(mangaContext, last);

        log.info("DB 저장...");
        mangaSaver.save(mangaContext);
        return response;
    }

    public MangaAnalyze.Response analyzeManga(List<MultipartFile> files) throws IOException {
        log.info("파일 저장...");
        String thumbnailPath = MangaMapper.EMPTY;
        MangaUpload.Request request = MangaMapper.emptyUploadRequest();

        List<String> names = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = FileUtils.save(file, request.getTitle(), String.valueOf(0));
            names.add(filename);
        }
        MangaAnalyze.Request analyzeRequest = MangaMapper.toAnalyze(thumbnailPath, request, 0, names);
        Map result = DataRequest.analyze(analyzeRequest);

        log.info("파싱...");
        MangaContext mangaContext = parseService.parse(result);

        log.info("분석...");
        return mangaAnalyzer.analyze(mangaContext, 0);
    }


    public List<RecentManga.Response> getRecentManga(Long memberId) {
        List<Tuple> tupleList = mangaRepository.findRecentManga(memberId);
        List<RecentManga.Response> response = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            response.add(RecentManga.Response.builder()
                    .id(tuple.get("id", Long.class))
                    .thumbnail(tuple.get("thumbnail", String.class))
                    .title(tuple.get("title", String.class))
                    .build());
        }
        return response;
    }

    public Page<LevelManga.Response> getLevelManga(int level, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mangaRepository.findAllByLevelOrderByIdDesc(level, pageable);
    }

    public Manga getMangaInfo(Long id) {
        return mangaRepository.findById(id).orElseThrow();
    }

    public Manga findById(Long mangaId) {
        return mangaRepository.findById(mangaId).orElseThrow();
    }

    public int calculateMangaLevel(long id) {
        Manga manga = mangaRepository.findMangaByIdFetchJoinChapter(id);

        int totalSum = 0;
        int totalCnt = manga.getChapter().size();

        for (Chapter chapter : manga.getChapter()) {
            if (chapter.getLevel() != null) {
                totalSum += chapter.getLevel();
            } else {
                System.out.println("chapter.getId() = " + chapter.getId());
            }
        }

        return averageToLevel(totalSum, totalCnt);
    }

    public int averageToLevel(int totalSum, int totalCnt) {
        double avg = (double) totalSum / totalCnt;

        if (avg < 1.485) {
            return 1;
        } else if (avg < 2.727) {
            return 2;
        } else if (avg < 3.676) {
            return 3;
        } else if (avg < 4.333) {
            return 4;
        } else {
            return 5;
        }
    }


    public List<LevelCount.Response> getMangaWordLevelCount(long mangaId) {
        List<Tuple> mangaLevelList = wordListService.findCalculatedMangaLevel(mangaId);

        // 모든 레벨에 대해 count를 0으로 초기화
        List<LevelCount.Response> result = new ArrayList<>();
        for (int level = 1; level <= 5; level++) {
            result.add(LevelCount.Response.builder()
                    .level(level)
                    .count(0)
                    .build()
            );
        }

        for (Tuple tuple : mangaLevelList) {
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

    //    @PostConstruct
    public void initSetLevel() {    // 기존 DB에 있지만, level이 부여 안된 manga들 level update
        for (Manga manga : mangaRepository.findAll()) {
            int mangaLevel = calculateMangaLevel(manga.getId());
            manga.setLevel(mangaLevel);
            mangaRepository.save(manga);
        }
    }

    public List<LevelCount.Response> getMangaChapterLevelCount(long mangaId) {
        List<Tuple> mangaChapterLevelList = wordListRepository.findCalculatedMangaChapterLevel(mangaId);

        // 모든 레벨에 대해 count를 0으로 초기화
        List<LevelCount.Response> result = new ArrayList<>();
        for (int level = 1; level <= 5; level++) {
            result.add(LevelCount.Response.builder()
                    .level(level)
                    .count(0)
                    .build()
            );
        }

        for (Tuple tuple : mangaChapterLevelList) {
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
}
