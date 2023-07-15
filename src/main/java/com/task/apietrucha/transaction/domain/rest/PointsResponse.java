package com.task.apietrucha.transaction.domain.rest;

import com.task.apietrucha.transaction.domain.rest.dto.PointsPerMonth;
import java.util.List;

public record PointsResponse(Integer total, List<PointsPerMonth> pointsPerMonth) {

}
