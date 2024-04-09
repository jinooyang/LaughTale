package com.jshi.laughtale.speech.domain;

import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.position.domain.Position;
import com.jshi.laughtale.wordlist.domain.WordList;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Getter
public class Speech {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sentence;

    @Column
    private Integer speechNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cut_id")
    @Setter
    private Cut cut;

    @OneToOne(cascade = PERSIST, optional = true)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "speech", orphanRemoval = true)
    @Setter
    private List<WordList> wordLists = new ArrayList<>();

}
