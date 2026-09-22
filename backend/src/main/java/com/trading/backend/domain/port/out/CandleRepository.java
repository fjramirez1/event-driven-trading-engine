package com.trading.backend.domain.port.out;

import com.trading.backend.domain.model.Candle;
import com.trading.backend.domain.model.CandleId;
import com.trading.backend.domain.model.Timeframe;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CandleRepository {
    Candle save(Candle candle);

    List<Candle> saveAll(List<Candle> candles);

    Optional<Candle> findById(CandleId id);

    List<Candle> findBySymbolAndTimeframeBetween(String symbol, Timeframe timeframe, Instant start, Instant end);
}