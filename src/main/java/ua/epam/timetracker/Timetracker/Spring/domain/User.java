package ua.epam.timetracker.Timetracker.Spring.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @Setter(AccessLevel.PUBLIC) private String password;

    @NotNull(message = "Please provide user role")
    private final Role role;
}
