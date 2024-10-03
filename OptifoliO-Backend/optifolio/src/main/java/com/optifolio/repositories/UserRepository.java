package com.optifolio.repositories;

import com.optifolio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByEmailId(String emailId);


    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.emailId = :emailId")
    boolean existsByEmailId(String emailId);
}
