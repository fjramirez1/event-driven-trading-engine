package com.trading.backend.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CandleEntityId implements Serializable {

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "timeframe", nullable = false)
    private String timeframe;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;
}