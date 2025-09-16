package com.charly.market.user.service;


import com.charly.market.global.constant.UserRole;
import com.charly.market.user.model.entity.User;
import com.charly.market.user.model.dto.CreateUserRequest;
import com.charly.market.user.model.dto.UserResponse;
import com.charly.market.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void signUp(CreateUserRequest request) {
    User user = User.builder()
                    .username(request.id())
                    .password(passwordEncoder.encode(request.password()))
                    .name(request.name())
                    .nickname(request.nickname())
                    .email(request.email())
                    .phone(request.phone())
                    .role(UserRole.USER)
                    .status("Y")
                    .balance(0)
                    .tradeCount(0)
                    .storedPoint(0)
                    .build();

    userRepository.save(user);
  }

  @Override
  public List<UserResponse> findAll() {
    List<User> users = userRepository.findAll();
    List<UserResponse> userResponseList = new ArrayList<>();
    for (User user : users) {
      UserResponse userResponse = UserResponse.from(user);
      userResponseList.add(userResponse);
    }

    return userResponseList;
  }

  @Override
  public UserResponse findByUsername(String username) {
    User user = userRepository.findByUsername(username);
    return UserResponse.from(user);
  }

  @Transactional
  @Override
  public void signOut(String username) {
    User user = userRepository.findByUsername(username);
    user.deactivatedUserStatus();
  }
}
