package com.louay.controller.factory;

import com.louay.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceFactory {
    private final UserService userService;

    @Autowired
    public ServiceFactory(UserService userService) {
        Assert.notNull(userService, "userService cannot be null!.");

        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
