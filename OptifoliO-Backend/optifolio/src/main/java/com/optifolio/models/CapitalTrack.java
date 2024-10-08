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
@Table(name="capital_tracking")
public class CapitalTrack {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = CustomIdGenerator.class)
    @Column(name = "capital_track_id", unique = true, nullable = false)
    private String capitalTrackId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "invested_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal investedAmount;

    @Column(name = "free_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal freeAmount;

    @Column(name = "total_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "amount_change", precision = 12, scale = 2, nullable = false)
    private BigDecimal amountChange;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
