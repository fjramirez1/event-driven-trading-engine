package com.trading.backend.infrastructure.adapter.out.persistence.repository;

import com.trading.backend.infrastructure.adapter.out.persistence.entity.CandleEntity;
import com.trading.backend.infrastructure.adapter.out.persistence.entity.CandleEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface SpringDataCandleRepository extends JpaRepository<CandleEntity, CandleEntityId> {

    @Query("SELECT c FROM CandleEntity c WHERE c.id.symbol = :symbol AND c.id.timeframe = :timeframe AND c.id.timestamp BETWEEN :start AND :end ORDER BY c.id.timestamp ASC")
    List<CandleEntity> findBySymbolAndTimeframeAndTimestampBetween(
            @Param("symbol") String symbol,
            @Param("timeframe") String timeframe,
            @Param("start") Instant start,
            @Param("end") Instant end
    );
}