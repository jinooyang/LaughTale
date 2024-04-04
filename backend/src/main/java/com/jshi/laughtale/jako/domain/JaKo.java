package com.jshi.laughtale.jako.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "jako")
@Getter
@Setter
public class JaKo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String langFrom;
    @Column
    private String langTo;
    @Column(columnDefinition = "TEXT")
    private String parsedDef;
}
