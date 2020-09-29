package com.louay.model.service.user.impl;

import com.louay.model.dao.user.UserDao;
import com.louay.model.entity.user.User;
import com.louay.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceCrud implements UserService, Serializable {
    private static final long serialVersionUID = 7196237813730380285L;
    private final UserDao userDao;

    @Autowired
    public UserServiceCrud(UserDao userDao) {
        Assert.notNull(userDao, "Dao cannot be null!.");

        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User createUser(User user) {
        return this.userDao.save(user);
    }

    @Transactional
    @Override
    public User deleteUserByUserId(User user) {
        return this.userDao.update(user);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return this.userDao.update(user);
    }

    @Transactional
    @Override
    public User findUserByUserId(User user) {
        return this.userDao.findOneById(user);
    }

    @Transactional
    @Override
    public List<User> findAllUser(User user) {
        return this.userDao.find100User();
    }
}
