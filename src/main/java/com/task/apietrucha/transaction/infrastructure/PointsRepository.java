package com.task.apietrucha.transaction.infrastructure;

import com.task.apietrucha.transaction.domain.entity.Points;
import com.task.apietrucha.transaction.domain.entity.Purchase;
import com.task.apietrucha.transaction.domain.projections.PointsProjection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<Points, Long> {

    Optional<Points> findByPurchase(Purchase purchase);

    @Query("select sum(p.points) as sumPerMonth, p.createdMonth as monthNumber, p.createdYear as yearNumber"
        + " from Points p"
        + " where p.customerId=:customerId and p.createdYear=:year and p.createdMonth > :month"
        + " group by p.createdMonth"
        + " order by p.createdMonth "
        + " desc limit 3")
    List<PointsProjection> findPointsFromLastThreeMonthsForCustomerId(Long customerId, int year, int month);

    @Query("select sum(p.points) from Points p where p.customerId=:customerId")
    Long findTotalPointsForCustomerId(Long customerId);
}