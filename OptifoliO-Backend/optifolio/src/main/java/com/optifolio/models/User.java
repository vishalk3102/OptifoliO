package com.optifolio.models;


import com.optifolio.components.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
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

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Enum.RoleType role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Position> positions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CapitalTrack> capitalTracks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false)
    private LocalDateTime  updatedAt;
}
