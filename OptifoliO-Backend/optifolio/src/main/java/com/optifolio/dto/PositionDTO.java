package com.optifolio.dto;


import com.optifolio.models.Enum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PositionDTO {
    @Column(name = "option_id", unique = true, nullable = false)
    private String optionId;

    @Column(name="trading_symbol",nullable=false)
    private String tradingSymbol;

    @Column(name = "strike_price", nullable = false)
    private Double strikePrice;

    @Column(name = "exchange", nullable = false)
    private String exchange;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_type", nullable = false)
    private Enum.OptionType optionType;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "average_buy_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal averageBuyPrice;

    @Column(name = "average_sell_price", precision = 10, scale = 2)
    private BigDecimal averageSellPrice;

    @Column(name = "total_buy_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalBuyValue;

    @Column(name = "current_value", precision = 10, scale = 2)
    private BigDecimal currentValue;

    @Column(name = "total_sell_value", precision = 10, scale = 2)
    private BigDecimal totalSellValue;

    @Column(name = "buy_date", nullable = false)
    private LocalDateTime buyDate;

    @Column(name = "sell_date")
    private LocalDateTime sellDate;

    @Column(name = "profit_loss", precision = 12, scale = 2)
    private BigDecimal profitLoss;

    @Column(name = "tax_charges", precision = 12, scale = 2)
    private BigDecimal taxCharges;

    @Column(name = "net_pnl", precision = 12, scale = 2)
    private BigDecimal netProfitLoss;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "status")
    private Boolean status=true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
