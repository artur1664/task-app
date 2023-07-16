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
        + " where p.customerId=:customerId and p.createdYear in (:years) and p.createdMonth in (:months)"
        + " group by p.createdYear, p.createdMonth"
        + " order by p.createdYear, p.createdMonth asc")
    List<PointsProjection> findPointsForCustomerByYearAndMonth(Long customerId, List<Integer> years, List<Integer> months);

    @Query("select sum(p.points) from Points p where p.customerId=:customerId")
    Long findTotalPointsForCustomerId(Long customerId);
}