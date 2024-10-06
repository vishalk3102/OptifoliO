package com.optifolio.dto;


import com.optifolio.components.CustomIdGenerator;
import com.optifolio.models.Enum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PositionCreateDTO {
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

    @Column(name = "buy_date", nullable = false)
    private LocalDateTime buyDate;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "status")
    private Boolean status=true;
}
