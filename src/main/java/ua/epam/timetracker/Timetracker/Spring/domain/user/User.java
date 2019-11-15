package ua.epam.timetracker.Timetracker.Spring.domain.user;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class User {
    private final Integer id;

    @NotEmpty(message = "Please provide user name")
    @Pattern(regexp = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))")
    private final String name;

    @NotEmpty(message = "Please provide user surname")
    @Pattern(regexp = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))")
    private final String surname;

    @Email(message = "Please provide a valid Email")
    @NotEmpty(message = "Please provide an email")
    private final String email;

    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty(message = "Please provide user password")
    private final String password;

    @NotEmpty(message = "Please provide user role")
    private final Role role;
}
