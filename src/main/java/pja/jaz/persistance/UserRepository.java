package pja.jaz.persistance;

import pja.jaz.model.User;

import java.util.List;

public interface UserRepository {

    void add(User user);
    void delete(User user);
    void update(User user);

    User findByUsername(String username);
    List<User> findAll();
}
