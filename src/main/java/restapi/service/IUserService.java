package restapi.service;

import restapi.model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findUserById(long id);

    User updateUser(User user);
}