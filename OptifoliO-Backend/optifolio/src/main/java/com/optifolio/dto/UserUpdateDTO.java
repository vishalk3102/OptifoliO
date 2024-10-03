package com.optifolio.dto;

import com.optifolio.components.CustomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;


@Data
public class UserUpdateDTO {
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
}
