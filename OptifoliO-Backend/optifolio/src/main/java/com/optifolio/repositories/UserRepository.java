package com.optifolio.repositories;

import com.optifolio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmailId(String emailId);

    User findByUserEmailId(String username);
}
