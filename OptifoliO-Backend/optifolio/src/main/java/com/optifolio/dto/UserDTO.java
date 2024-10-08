package com.optifolio.dto;

import com.optifolio.components.CustomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;


@Data
public class UserDTO {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = CustomIdGenerator.class)
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name="full_name", nullable = false)
    private String name;

    @Column(name="user_broker_id", unique = true,nullable = false)
    private String brokerId;

    @Column(name="email_id",unique = true, nullable = false)
    private String emailId;

    @Column(name="password", nullable = false)
    private String password;

//    @Column(name="profile_url", nullable = false)
//    private String profile_url;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false)
    private LocalDateTime  updatedAt;
}
