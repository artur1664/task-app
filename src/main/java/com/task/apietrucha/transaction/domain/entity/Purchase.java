package com.task.apietrucha.transaction.domain.entity;

import com.task.apietrucha.transaction.domain.rest.CreatePurchaseRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "purchase")
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Version
    private Integer version;

    public static Purchase from(CreatePurchaseRequest request) {
        return Purchase.builder()
            .amount(request.amount())
            .customerId(request.customerId())
            .build();
    }
}
