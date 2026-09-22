package com.trading.backend.domain.port.in;

import com.trading.backend.domain.model.Candle;
import com.trading.backend.domain.model.Timeframe;

import java.time.Instant;
import java.util.List;

public interface GetCandlesUseCase {
    List<Candle> execute(String symbol, Timeframe timeframe, Instant start, Instant end);
}