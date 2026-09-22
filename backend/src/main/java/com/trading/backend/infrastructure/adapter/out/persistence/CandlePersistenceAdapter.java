package com.trading.backend.infrastructure.adapter.out.persistence;

import com.trading.backend.domain.model.Candle;
import com.trading.backend.domain.model.CandleId;
import com.trading.backend.domain.model.Timeframe;
import com.trading.backend.domain.port.out.CandleRepository;
import com.trading.backend.infrastructure.adapter.out.persistence.entity.CandleEntity;
import com.trading.backend.infrastructure.adapter.out.persistence.entity.CandleEntityId;
import com.trading.backend.infrastructure.adapter.out.persistence.mapper.CandlePersistenceMapper;
import com.trading.backend.infrastructure.adapter.out.persistence.repository.SpringDataCandleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CandlePersistenceAdapter implements CandleRepository {

    private final SpringDataCandleRepository springDataCandleRepository;
    private final CandlePersistenceMapper mapper;

    @Override
    @Transactional
    public Candle save(Candle candle) {
        log.debug("Saving candle for symbol: {}, timeframe: {}, timestamp: {}",
                candle.getId().getSymbol(), candle.getId().getTimeframe(), candle.getId().getTimestamp());
        CandleEntity entity = mapper.toEntity(candle);
        CandleEntity savedEntity = springDataCandleRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public List<Candle> saveAll(List<Candle> candles) {
        log.debug("Saving batch of {} candles", candles.size());
        List<CandleEntity> entities = candles.stream()
                .map(mapper::toEntity)
                .toList();
        List<CandleEntity> savedEntities = springDataCandleRepository.saveAll(entities);
        return savedEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Candle> findById(CandleId id) {
        CandleEntityId entityId = new CandleEntityId(
                id.getTimestamp(),
                id.getSymbol(),
                id.getTimeframe().getCode()
        );
        return springDataCandleRepository.findById(entityId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candle> findBySymbolAndTimeframeBetween(String symbol, Timeframe timeframe, Instant start, Instant end) {
        List<CandleEntity> entities = springDataCandleRepository.findBySymbolAndTimeframeAndTimestampBetween(
                symbol.toUpperCase(),
                timeframe.getCode(),
                start,
                end
        );
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}