package com.trading.backend.infrastructure.adapter.in.rest.dto;

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
}