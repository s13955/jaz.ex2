package pja.jaz;

import pja.jaz.model.Role;
import pja.jaz.model.User;

public class DataHelper {

    protected User createUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@example.com");
        user.setRole(Role.USER);
        return user;
    }
}
