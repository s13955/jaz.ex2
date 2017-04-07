package pja.jaz.persistance;

import org.junit.Before;
import org.junit.Test;
import pja.jaz.DataHelper;
import pja.jaz.model.User;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryUserRepositoryTest extends DataHelper {

    private UserRepository repository;

    @Before
    public void setup() {
        repository = new InMemoryUserRepository();
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_NullAsUser_ThrownException() {
        repository.add(null);
    }

    @Test
    public void add_TestUser_UserAdded() {
        User user = createUser();
        repository.add(user);
        assertThat(repository.findByUsername(user.getUsername())).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_NullAsUser_ThrownException() {
        repository.delete(null);
    }

    @Test
    public void delete_TestUser_UserDeleted() {
        User user = new User("a", null, null,  null);
        repository.delete(user);
        assertThat(repository.findAll()).doesNotContain(user);
    }

    @Test
    public void findByUsername_NullAsUsername_Null() {
        User user = repository.findByUsername(null);
        assertThat(user).isNull();
    }

    @Test
    public void findAll_ReturnAllUser_ReturnedList() {
        assertThat(repository.findAll())
                .isNotNull()
                .isNotEmpty()
        ;
    }
}
