package com.task.apietrucha.transaction.domain.rest.dto;

import com.task.apietrucha.transaction.domain.projections.PointsProjection;
import java.util.Collections;
import java.util.List;
import lombok.Builder;

@Builder
public record PointsPerMonth(String month, Integer points) {

    public static List<PointsPerMonth> from(List<PointsProjection> projection) {
        int numberOfMonthsToDisplay = 3;
        if (projection == null || projection.isEmpty()) {
            return Collections.emptyList();
        } else if (projection.size() <= numberOfMonthsToDisplay) {
            return projection.stream().map(PointsPerMonth::from).toList();
        } else {
            //returned projection is sorted so return last results
            return projection.subList(projection.size() - numberOfMonthsToDisplay, projection.size())
                .stream().map(PointsPerMonth::from).toList();
        }
    }

    private static PointsPerMonth from(PointsProjection projection) {
        return PointsPerMonth.builder()
            .month(formatMonth(projection.getYearNumber(), projection.getMonthNumber()))
            .points(projection.getSumPerMonth())
            .build();
    }

    private static String formatMonth(Integer yearNumber, Integer monthNumber) {
        return String.format("%s-%s", yearNumber, monthNumber);
    }
}
