package com.trading.backend.infrastructure.adapter.in.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class ErrorResponseDTO {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
}