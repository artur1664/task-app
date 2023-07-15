package com.task.apietrucha.transaction.infrastructure;

import com.task.apietrucha.transaction.domain.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<Points, Long> {

}