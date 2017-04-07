package pja.jaz.servlet.filters;

import org.junit.Test;
import pja.jaz.DataHelper;
import pja.jaz.model.User;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseFilterTest extends DataHelper {

    @Test
    public void isApplicationPath_Null_False() {
        boolean result = new BaseFilter().isApplicationPath(null);
        assertThat(result).isFalse();
    }

    @Test
    public void isApplicationPath_CorrectApplicationPath_True() {
        boolean result = new BaseFilter().isApplicationPath("/css");
        assertThat(result).isTrue();
    }

    @Test
    public void isApplicationPath_WrongPath_False() {
        boolean result = new BaseFilter().isApplicationPath("/admin/premium");
        assertThat(result).isFalse();
    }

    @Test
    public void getUser_Null_Null() {
        User result = new BaseFilter().getSessionUser(null);
        assertThat(result).isNull();
    }

    @Test
    public void getUser_NormalUser_TheSameObject() {
        User user = createUser();

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);

        User result = new BaseFilter().getSessionUser(session);
        assertThat(result).isSameAs(user);

    }

    @Test
    public void isUser_NullSession_False() {
        boolean result = new BaseFilter().isSignedInUser(null);
        assertThat(result).isFalse();
    }

    @Test
    public void isUser_AnonymousUser_False() {
        HttpSession session = mock(HttpSession.class);
        boolean result = new BaseFilter().isSignedInUser(session);

        assertThat(result).isFalse();
    }

    @Test
    public void isUser_SignedInUser_True() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(createUser());
        boolean result = new BaseFilter().isSignedInUser(session);

        assertThat(result).isTrue();
    }
}
