package com.optifolio.models;

import com.optifolio.components.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = CustomIdGenerator.class)
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
