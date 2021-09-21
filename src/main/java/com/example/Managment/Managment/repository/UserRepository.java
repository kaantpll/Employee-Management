package com.example.Managment.Managment.repository;

import com.example.Managment.Managment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
