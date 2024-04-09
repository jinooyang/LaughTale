package com.jshi.laughtale.wordbook.domain;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.worddata.domain.WordData;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Getter
public class WordBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "word_id")
    private WordData wordData;
}
