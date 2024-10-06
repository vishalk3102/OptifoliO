package com.optifolio.models;

import com.optifolio.components.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = CustomIdGenerator.class)
    @Column(name = "comment_id", unique = true, nullable = false)
    private String commentId;

    @Column(name="text_content", nullable = false,length = 500)
    private String textContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "comment")
    private Portfolio portfolio;

    @OneToOne(mappedBy = "comment")
    private Position position;

    @OneToOne(mappedBy = "comment")
    private CapitalTrack capitalTrack;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false)
    private LocalDateTime  updatedAt;

}
