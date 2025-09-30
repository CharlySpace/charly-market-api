package com.charly.market.user.service.util;

import com.charly.market.user.model.entity.User;
import com.charly.market.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFinder {
  private final UserRepository userRepository;

  public User getById(Long id) {
    return userRepository.findById(id).orElseThrow();
  }

  public User getByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
