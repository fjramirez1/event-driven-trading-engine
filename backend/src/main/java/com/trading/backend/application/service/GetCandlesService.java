package com.trading.backend.application.service;

import com.trading.backend.domain.model.Candle;
import com.trading.backend.domain.model.Timeframe;
import com.trading.backend.domain.port.in.GetCandlesUseCase;
import com.trading.backend.domain.port.out.CandleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetCandlesService implements GetCandlesUseCase {

    private final CandleRepository candleRepository;

    @Override
    public List<Candle> execute(String symbol, Timeframe timeframe, Instant start, Instant end) {
        validateRange(start, end);
        log.info("Fetching candles for symbol: {}, timeframe: {}, from: {} to: {}", symbol, timeframe, start, end);
        return candleRepository.findBySymbolAndTimeframeBetween(symbol, timeframe, start, end);
    }

    private void validateRange(Instant start, Instant end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end dates are required");
        }
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }
}