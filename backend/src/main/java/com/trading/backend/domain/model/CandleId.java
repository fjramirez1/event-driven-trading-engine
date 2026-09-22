package com.trading.backend.domain.model;

import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class CandleId {
    private final String symbol;
    private final Timeframe timeframe;
    private final Instant timestamp;

    public CandleId(String symbol, Timeframe timeframe, Instant timestamp) {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("Symbol cannot be null or blank");
        }
        this.symbol = symbol.toUpperCase();
        this.timeframe = Objects.requireNonNull(timeframe, "Timeframe is required");
        this.timestamp = Objects.requireNonNull(timestamp, "Timestamp is required");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandleId candleId = (CandleId) o;
        return Objects.equals(symbol, candleId.symbol) && timeframe == candleId.timeframe && Objects.equals(timestamp, candleId.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, timeframe, timestamp);
    }
}