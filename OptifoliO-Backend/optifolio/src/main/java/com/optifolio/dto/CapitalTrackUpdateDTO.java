package com.optifolio.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class CapitalTrackUpdateDTO {
    @Column(name = "capital_track_id", unique = true, nullable = false)
    private String capitalTrackId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "invested_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal investedAmount;

    @Column(name = "free_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal freeAmount;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
}
