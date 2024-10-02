package com.optifolio.dto;

import com.optifolio.models.Enum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PortfolioUpdateDTO {

    @Column(name = "holding_id", unique = true, nullable = false)
    private String holdingId;

    @Column(name="trading_symbol",nullable=false)
    private String tradingSymbol;

    @Column(name = "exchange", nullable = false)
    private String exchange;

    @Enumerated(EnumType.STRING)
    @Column(name = "instrument_type", nullable = false)
    private Enum.InstrumentType instrumentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Enum.CategoryType category;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "average_buy_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal averageBuyPrice;

    @Column(name = "average_sell_price", precision = 10, scale = 2)
    private BigDecimal averageSellPrice;

    @Column(name = "buy_date", nullable = false)
    private LocalDateTime buyDate;

    @Column(name = "sell_date")
    private LocalDateTime sellDate;

    @Column(name = "tax_charges", precision = 12, scale = 2)
    private BigDecimal taxCharges;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "status")
    private Boolean status=true;
}
