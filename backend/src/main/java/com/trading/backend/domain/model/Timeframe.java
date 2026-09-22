package com.trading.backend.domain.model;

import lombok.Getter;

@Getter
public enum Timeframe {
    M1("1m"),
    M5("5m"),
    M15("15m"),
    H1("1h"),
    H4("4h"),
    D1("1d");

    private final String code;

    Timeframe(String code) {
        this.code = code;
    }

    public static Timeframe fromCode(String code) {
        for (Timeframe tf : values()) {
            if (tf.code.equalsIgnoreCase(code)) {
                return tf;
            }
        }
        throw new IllegalArgumentException("Unsupported timeframe code: " + code);
    }
}