package project.domain;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    @NotEmpty(message = "Please provide user name")
    @Pattern(regexp = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))")
    private String name;

    @NotEmpty(message = "Please provide user surname")
    @Pattern(regexp = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))")
    private String surname;

    @Email(message = "Please provide a valid Email")
    @NotEmpty(message = "Please provide an email")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,15}$", message = "Password must be longer " +
            "than 5 characters and consist at least 1 upper case character, and at least 1 number")
    @NotEmpty(message = "Please provide user password")
    @Setter(AccessLevel.PUBLIC) private String password;

    @NotNull(message = "Please provide user role")
    @Setter(AccessLevel.PUBLIC) private Role role;
}
