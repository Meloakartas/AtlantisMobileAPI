package restapi.service;

import restapi.model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findUserById(long id);

    User saveOrUpdateUser(User user);

    User findUserByUserADid(String userADid);
}