package pja.jaz.persistance;

import pja.jaz.model.Role;
import pja.jaz.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

    private static HashMap<String, User> db = new HashMap<>();

    static {
        db.put("a", new User("a", "a", "a@example.com", Role.ADMIN));
        db.put("admin", new User("admin", "123", "admin@example.com", Role.ADMIN));
        db.put("user", new User("user", "123", "user@example.com", Role.USER));
        db.put("premium", new User("premium", "123", "premium@example.com", Role.PREMIUM));
    }

    private String getPrimaryKey(User user) {
        return user.getUsername();
    }

    @Override
    public void add(User user) {
        if (null == user) {
            throw new IllegalArgumentException("Null value was given!");
        }

        String key = getPrimaryKey(user);

        if (db.containsKey(key)) {
            throw new IllegalStateException("Duplicate user!");
        }

        db.put(key, user);
    }

    @Override
    public void delete(User user) {
        if (null == user) {
            throw new IllegalArgumentException("Null value was given!");
        }

        db.remove(getPrimaryKey(user));
    }

    @Override
    public void update(User user) {
        db.replace(getPrimaryKey(user), user);
    }

    @Override
    public User findByUsername(String username) {
        return db.get(username);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }
}
