package com.trading.backend.infrastructure.adapter.in.rest;

import com.trading.backend.domain.model.Timeframe;
import com.trading.backend.domain.port.in.GetCandlesUseCase;
import com.trading.backend.infrastructure.adapter.in.rest.dto.CandleResponseDTO;
import com.trading.backend.infrastructure.adapter.in.rest.mapper.CandleRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/candles")
@RequiredArgsConstructor
public class CandleRestController {

    private final GetCandlesUseCase getCandlesUseCase;
    private final CandleRestMapper candleRestMapper;

    @GetMapping
    public ResponseEntity<List<CandleResponseDTO>> getCandles(
            @RequestParam String symbol,
            @RequestParam String timeframe,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {

        Timeframe tf = Timeframe.fromCode(timeframe);
        List<CandleResponseDTO> response = getCandlesUseCase.execute(symbol, tf, start, end)
                .stream()
                .map(candleRestMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}