package com.task.apietrucha.transaction.domain.rest.dto;

import com.task.apietrucha.transaction.domain.projections.PointsProjection;
import lombok.Builder;

@Builder
public record PointsPerMonth(String month, Integer points) {

    public static PointsPerMonth from(PointsProjection projection) {
        return PointsPerMonth.builder()
            .month(formatMonth(projection.getYearNumber(), projection.getMonthNumber()))
            .points(projection.getSumPerMonth())
            .build();
    }

    private static String formatMonth(Integer yearNumber, Integer monthNumber) {
        return String.format("%s-%s", yearNumber, monthNumber);
    }
}
