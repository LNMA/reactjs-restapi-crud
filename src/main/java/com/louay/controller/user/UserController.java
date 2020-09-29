package com.louay.controller.user;

import com.louay.controller.factory.EntityFactory;
import com.louay.controller.factory.ServiceFactory;
import com.louay.model.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController implements Serializable {
    private static final long serialVersionUID = 32422564219225021L;
    private final EntityFactory entityFactory;
    private final ServiceFactory serviceFactory;

    @Autowired
    public UserController(EntityFactory entityFactory, ServiceFactory serviceFactory) {
        Assert.notNull(entityFactory, "entityFactory cannot be null!.");
        Assert.notNull(serviceFactory, "serviceFactory cannot be null!.");

        this.entityFactory = entityFactory;
        this.serviceFactory = serviceFactory;
    }

    @RequestMapping(value = "/user/update-user", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> EditUser(@RequestBody User user) {
        Assert.notNull(user, "user cannot be null!.");

        updateUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(String.format("User ID %d updated successfully.", user.getUserId()));
    }

    private void updateUser(User user) {
        this.serviceFactory.getUserService().updateUser(user);
    }

    @GetMapping(value = "/user/{userId}/get-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    User retrieveOneUser(@PathVariable(value = "userId") String userIdInPath) {
        Assert.notNull(userIdInPath, "userId cannot be null!.");

        Long userId = Long.valueOf(userIdInPath);

        return findOneUser(userId);
    }

    private User findOneUser(Long userId) {
        User user = buildUser(userId);

        return this.serviceFactory.getUserService().findUserByUserId(user);
    }

    @RequestMapping(value = "/user/{userId}/delete-user", method = RequestMethod.DELETE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> removeUser(@PathVariable(value = "userId") String userIdInPath) {
        Assert.notNull(userIdInPath, "userId cannot be null!.");

        Long userId = Long.valueOf(userIdInPath);
        deleteUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(String.format("User ID %d deleted successfully.", userId));
    }

    private void deleteUser(Long userId) {
        User user = buildUser(userId);

        this.serviceFactory.getUserService().deleteUserByUserId(user);
    }

    private User buildUser(Long userId) {
        User user = this.entityFactory.getUser();
        user.setUserId(userId);

        return user;
    }

    @GetMapping(value = "/user/get-all-users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    List<User> retrieveAllUser() {
        return findAllUser();
    }

    private List<User> findAllUser() {
        return this.serviceFactory.getUserService().findAllUser();
    }

    @PostMapping(value = "/user/create-user", produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> saveUser(@RequestBody User user) {
        Assert.notNull(user, "user cannot be null!.");

        User userPersist = createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("User ID %d created successfully", userPersist.getUserId()));
    }

    private User createUser(User user) {
        return this.serviceFactory.getUserService().createUser(user);
    }
}
