package com.trading.backend.infrastructure.adapter.in.rest.mapper;

import com.trading.backend.domain.model.Candle;
import com.trading.backend.infrastructure.adapter.in.rest.dto.CandleResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CandleRestMapper {

    public CandleResponseDTO toResponse(Candle candle) {
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