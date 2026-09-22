package com.trading.backend.infrastructure.adapter.out.persistence.mapper;

import com.trading.backend.domain.model.Candle;
import com.trading.backend.domain.model.CandleId;
import com.trading.backend.domain.model.Timeframe;
import com.trading.backend.infrastructure.adapter.out.persistence.entity.CandleEntity;
import com.trading.backend.infrastructure.adapter.out.persistence.entity.CandleEntityId;
import org.springframework.stereotype.Component;

@Component
public class CandlePersistenceMapper {

    public CandleEntity toEntity(Candle candle) {
        if (candle == null) {
            return null;
        }

        CandleEntityId entityId = new CandleEntityId(
                candle.getId().getTimestamp(),
                candle.getId().getSymbol(),
                candle.getId().getTimeframe().getCode()
        );

        return new CandleEntity(
                entityId,
                candle.getOpen(),
                candle.getHigh(),
                candle.getLow(),
                candle.getClose(),
                candle.getVolume()
        );
    }

    public Candle toDomain(CandleEntity entity) {
        if (entity == null) {
            return null;
        }

        CandleId domainId = new CandleId(
                entity.getId().getSymbol(),
                Timeframe.fromCode(entity.getId().getTimeframe()),
                entity.getId().getTimestamp()
        );

        return new Candle(
                domainId,
                entity.getOpen(),
                entity.getHigh(),
                entity.getLow(),
                entity.getClose(),
                entity.getVolume()
        );
    }
}