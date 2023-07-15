package com.task.apietrucha.transaction.domain.rest;

import com.task.apietrucha.transaction.domain.projections.PointsProjection;
import com.task.apietrucha.transaction.domain.rest.dto.PointsPerMonth;
import java.util.List;
import lombok.Builder;

@Builder
public record PointsResponse(Long total, List<PointsPerMonth> pointsPerMonth) {

    public static PointsResponse from(List<PointsProjection> pointsProjection, Long total) {
        return PointsResponse.builder()
            .total(total)
            .pointsPerMonth(pointsProjection.stream().map(PointsPerMonth::from).toList())
            .build();
    }
}
