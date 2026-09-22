package com.trading.backend.infrastructure.adapter.in.rest.dto;

import com.trading.backend.domain.model.Candle;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
public class CandleResponseDTO {
    private String symbol;
    private String timeframe;
    private Instant timestamp;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;

    public static CandleResponseDTO fromDomain(Candle candle) {
        return CandleResponseDTO.builder()
                .symbol(candle.getId().getSymbol())
                .timeframe(candle.getId().getTimeframe().getCode())
                .timestamp(candle.getId().getTimestamp())
                .open(candle.getOpen())
                .high(candle.getHigh())
                .low(candle.getLow())
                .close(candle.getClose())
                .volume(candle.getVolume())
                .build();
    }
}