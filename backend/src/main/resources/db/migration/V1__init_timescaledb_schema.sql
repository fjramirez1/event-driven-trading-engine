-- 1. Habilitar la extensión de TimescaleDB
CREATE EXTENSION IF NOT EXISTS timescaledb CASCADE;

-- 2. Tabla para Velas OHLCV (Series Temporales)
CREATE TABLE candles
(
    timestamp   TIMESTAMPTZ    NOT NULL,
    symbol      VARCHAR(20)    NOT NULL,
    timeframe   VARCHAR(10)    NOT NULL, -- e.g., '1m', '5m', '1h', '1d'
    open_price  NUMERIC(18, 8) NOT NULL,
    high_price  NUMERIC(18, 8) NOT NULL,
    low_price   NUMERIC(18, 8) NOT NULL,
    close_price NUMERIC(18, 8) NOT NULL,
    volume      NUMERIC(24, 8) NOT NULL,
    PRIMARY KEY (timestamp, symbol, timeframe)
);

-- Convertir 'candles' en Hypertable dividida por tiempo (chunks de 7 días por defecto)
SELECT create_hypertable('candles', 'timestamp', if_not_exists => TRUE);

-- Índice para consultas rápidas de velas por símbolo y temporalidad
CREATE INDEX idx_candles_symbol_timeframe_time
    ON candles (symbol, timeframe, timestamp DESC);

-- 3. Tabla para Señales de Trading Emitidas (Histórico Asistido)
CREATE TABLE trading_signals
(
    id            UUID PRIMARY KEY        DEFAULT gen_random_uuid(),
    timestamp     TIMESTAMPTZ    NOT NULL,
    symbol        VARCHAR(20)    NOT NULL,
    signal_type   VARCHAR(10)    NOT NULL,                  -- 'BUY', 'SELL'
    strategy_name VARCHAR(50)    NOT NULL,
    entry_price   NUMERIC(18, 8) NOT NULL,
    stop_loss     NUMERIC(18, 8),
    take_profit   NUMERIC(18, 8),
    timeframe     VARCHAR(10)    NOT NULL,
    status        VARCHAR(20)    NOT NULL DEFAULT 'PENDING' -- 'PENDING', 'EXECUTED', 'EXPIRED'
);

CREATE INDEX idx_signals_symbol_time ON trading_signals (symbol, timestamp DESC);