package com.louay.model.dao.user;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.user.User;

import java.util.List;

public interface UserDao extends CommonDao<User>, GenericDao<User> {
    List<User> find100User();
}
