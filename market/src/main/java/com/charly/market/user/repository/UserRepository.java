package com.charly.market.user.repository;

import com.charly.market.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserEmail(String userEmail);
}
