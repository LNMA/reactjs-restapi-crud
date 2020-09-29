package com.louay.model.service.user;

import com.louay.model.entity.user.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User deleteUserByUserId(User user);

    User updateUser(User user);

    User findUserByUserId(User user);

    List<User> findAllUser();
}
