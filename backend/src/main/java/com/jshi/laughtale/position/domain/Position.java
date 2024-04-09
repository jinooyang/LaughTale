package com.jshi.laughtale.position.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Getter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer leftTopX;
    @Column
    private Integer leftTopY;

    @Column
    private Integer rightTopX;
    @Column
    private Integer rightTopY;

    @Column
    private Integer leftBottomX;
    @Column
    private Integer leftBottomY;

    @Column
    private Integer rightBottomX;
    @Column
    private Integer rightBottomY;
}
