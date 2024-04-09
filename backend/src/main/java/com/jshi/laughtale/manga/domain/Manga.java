package com.jshi.laughtale.manga.domain;

import com.jshi.laughtale.chapter.domain.Chapter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table
@Getter
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Column
    private String author;
    @Column(length = 10000)
    private String description;
    @Column
    private Integer chapterCnt;
    @Column
    private String category;

    @Column(length = 1000)
    private String thumbnail;

    @Column
    @Setter
    private Integer level;

    @OneToMany(orphanRemoval = true, mappedBy = "manga")
    private List<Chapter> chapter = new ArrayList<>();

    public void updateChapter(List<Chapter> chapter) {
        this.chapter = chapter;
        this.chapterCnt = chapter.size();
    }
}
