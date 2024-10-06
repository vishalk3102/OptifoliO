package com.optifolio.dto;

import com.optifolio.components.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @Column(name = "comment_id", unique = true, nullable = false)
    private String commentId;

    @Column(name="text_content", nullable = false,length = 500)
    private String textContent;

    @Column(name="user_id",nullable = false)
    private String userId;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false)
    private LocalDateTime  updatedAt;

}