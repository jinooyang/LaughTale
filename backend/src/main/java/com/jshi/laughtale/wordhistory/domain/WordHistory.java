package com.jshi.laughtale.wordhistory.domain;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.worddata.domain.WordData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Getter
@Setter
public class WordHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer studyCnt;

    @Column
    private Integer offset;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime studyDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "word_id")
    private WordData wordData;

}
