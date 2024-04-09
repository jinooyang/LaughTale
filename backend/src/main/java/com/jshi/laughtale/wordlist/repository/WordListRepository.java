package com.jshi.laughtale.wordlist.repository;

import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.wordlist.domain.WordList;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordListRepository extends JpaRepository<WordList, Long> {

    @Query(value =
            "SELECT x.*, z.id AS speech_id, z.sentence AS sentence , w.definition AS definition, v.width AS width, v.height AS height  FROM (\n"
                    + "SELECT "
                    + "    e.id AS word_data_id, "
                    + "    e.word AS answer_word,"
                    + "    e.level AS level, "
                    + "    MAX(d.id) AS word_list_id "
                    + "FROM "
                    + "    chapter a "
                    + "INNER JOIN "
                    + "    cut b ON a.id = b.chapter_id "
                    + "INNER JOIN "
                    + "    speech c ON b.id = c.cut_id "
                    + "INNER JOIN "
                    + "    word_list d ON c.id = d.speech_id "
                    + "INNER JOIN "
                    + "    word_data e ON d.word_id = e.id "
                    + "WHERE "
                    + "    e.level = :level AND a.id = :chapterId AND e.definition IS NOT NULL "
                    + "GROUP BY "
                    + "    word_data_id, "
                    + "    answer_word, "
                    + "    level "
                    + "ORDER BY "
                    + "    word_data_id) x , word_list y, speech z, word_data w, cut v "
                    + "where x.word_list_id = y.id and y.speech_id = z.id and x.word_data_id = w.id and z.cut_id = v.id ", nativeQuery = true)
    List<Tuple> findWordListsWithLevel(int level, long chapterId);


    @Query(value = "SELECT wl FROM WordList wl WHERE wl.speech.id = :speechId")
    List<WordList> findAllBySpeechId(Long speechId);

    List<WordList> findAllByWordData(WordData wordData);

    List<WordList> findByWordDataIdAndSpeechId(Long wordId, Long speechId);


    @Query(value =
            "select ch.id as chapterId, wd.level, count(wd.level) as levelcnt from chapter ch,cut cu, speech sp, word_list wl, word_data wd\n"
                    + "         where ch.id  = cu.chapter_id and cu.id = sp.cut_id and wl.speech_id = sp.id and wl.word_id = wd.id\n"
                    + "          and ch.id = :chapterId \n"
                    + "         group by ch.id, wd.level order by ch.id, wd.level", nativeQuery = true)
    List<Tuple> findCalculatedChapterLevel(Long chapterId);

    @Query(value = "select wd.level, count(wd.level) as levelcnt"
            + " from manga ma, chapter ch,cut cu, speech sp, word_list wl, word_data wd"
            + " where ch.manga_id = ma.id and ch.id  = cu.chapter_id and cu.id = sp.cut_id and wl.speech_id = sp.id and wl.word_id = wd.id"
            + " and ma.id = :mangaId "
            + " group by wd.level order by wd.level", nativeQuery = true)
    List<Tuple> findCalculatedMangaLevel(Long mangaId);

    @Query(value = "select ch.level, count(ch.level) as levelcnt"
            + " from manga ma, chapter ch"
            + " where ch.manga_id = ma.id"
            + " and ma.id = :mangaId "
            + " group by ch.level order by ch.level", nativeQuery = true)
    List<Tuple> findCalculatedMangaChapterLevel(Long mangaId);
}
