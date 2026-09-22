package com.trading.backend.domain.model;

import jakarta.persistence.Column;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class Candle {
    private final CandleId id;
    @Column(name = "open_price")
    private BigDecimal open;
    @Column(name = "high_price")
    private BigDecimal high;
    @Column(name = "low_price")
    private BigDecimal low;
    @Column(name = "close_price")
    private BigDecimal close;
    private final BigDecimal volume;

    public Candle(CandleId id, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal volume) {
        this.id = Objects.requireNonNull(id, "Candle ID is required");
        this.open = Objects.requireNonNull(open, "Open price is required");
        this.high = Objects.requireNonNull(high, "High price is required");
        this.low = Objects.requireNonNull(low, "Low price is required");
        this.close = Objects.requireNonNull(close, "Close price is required");
        this.volume = Objects.requireNonNull(volume, "Volume is required");
        validatePrices();
    }

    private void validatePrices() {
        if (high.compareTo(open) < 0 || high.compareTo(close) < 0 || high.compareTo(low) < 0) {
            throw new IllegalArgumentException("High price must be the highest value in the candle");
        }
        if (low.compareTo(open) > 0 || low.compareTo(close) > 0) {
            throw new IllegalArgumentException("Low price must be the lowest value in the candle");
        }
        if (volume.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Volume cannot be negative");
        }
    }

    public boolean isBullish() {
        return close.compareTo(open) > 0;
    }

    public boolean isBearish() {
        return close.compareTo(open) < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candle candle = (Candle) o;
        return Objects.equals(id, candle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}