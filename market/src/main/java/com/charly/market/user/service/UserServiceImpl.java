package com.charly.market.user.service;


import com.charly.market.global.constant.UserRole;
import com.charly.market.user.model.User;
import com.charly.market.user.model.dto.CreateUserRequest;
import com.charly.market.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public void signUp(CreateUserRequest request) {
    User user = User.builder()
                    .userNickname(request.userNickname())
                    .id(request.id())
                    .userEmail(request.userEmail())
                    .userPhone(request.userPhone())
                    .userPassword(request.userPassword())
                    .userName(request.userName())
                    .userRole(UserRole.USER)
                    .userBalance(0)
                    .tradeCount(0)
                    .storedPoint(0)
                    .build();

    userRepository.save(user);
  }
}
