package project.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class RoleTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void valueOfNameShouldReturnRole() {
        Role actual = Role.valueOfName("DEVELOPER");

        assertThat(actual, equalTo(Role.DEVELOPER));
    }

    @Test
    public void valueOfNameShouldThrowIllegalArgumentExceptionWithNullParam() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Value of name role is null or there are no match by this name");

        Role.valueOfName(null);
    }

    @Test
    public void valueOfNameShouldThrowIllegalArgumentExceptionWithInvalidString() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Value of name role is null or there are no match by this name");

        Role.valueOfName("test");
    }

}