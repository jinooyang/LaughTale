package com.jshi.laughtale.worddata.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Getter
public class WordData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String word;
    @Column
    @Setter
    private Integer level;
    @Column
    private String partOfSpeech;
    @Column
    private Integer frequency;
    @Column(columnDefinition = "TEXT")
    @Setter
    private String definition;

    public void updateFrequency() {
        this.frequency++;
    }
}
