package com.jshi.laughtale.position.mapper;

import com.jshi.laughtale.position.domain.Position;
import com.jshi.laughtale.position.dto.PositionBasic;
import com.jshi.laughtale.position.exception.PositionInsufficientQuantityException;

import java.util.List;

public class PositionMapper {

    public static PositionBasic.Response toBasicResponse(Position position) {
        return PositionBasic.Response.builder()
                .leftTopY(position.getLeftTopY())
                .leftTopX(position.getRightTopX())
                .rightTopY(position.getRightTopY())
                .rightTopX(position.getRightTopX())
                .leftBottomY(position.getLeftBottomY())
                .leftBottomX(position.getLeftBottomX())
                .rightBottomY(position.getRightBottomY())
                .rightBottomX(position.getRightBottomX())
                .build();
    }

    public static Position listToPosition(List<Integer> positions) {
        if (positions.size() == 4) {
            return position4(positions);
        } else if (positions.size() == 8) {
            return position8(positions);
        }
        throw new PositionInsufficientQuantityException();
    }

    private static Position position4(List<Integer> positions) {
        return Position.builder()
                .leftTopX(positions.get(0))
                .leftTopY(positions.get(1))
                .rightBottomX(positions.get(2))
                .rightBottomY(positions.get(3))
                .leftBottomX(positions.get(0))
                .leftBottomY(positions.get(3))
                .rightTopX(positions.get(2))
                .rightTopY(positions.get(1))
                .build();
    }

    private static Position position8(List<Integer> positions) {
        return Position.builder()
                .leftTopX(positions.get(0))
                .leftTopY(positions.get(1))
                .rightTopX(positions.get(2))
                .rightTopY(positions.get(3))
                .rightBottomX(positions.get(4))
                .rightBottomY(positions.get(5))
                .leftBottomX(positions.get(6))
                .leftBottomY(positions.get(7))
                .build();
    }
}
