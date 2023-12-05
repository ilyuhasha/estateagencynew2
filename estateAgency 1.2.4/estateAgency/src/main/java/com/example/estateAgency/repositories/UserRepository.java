package com.example.estateAgency.repositories;


import com.example.estateAgency.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
}

