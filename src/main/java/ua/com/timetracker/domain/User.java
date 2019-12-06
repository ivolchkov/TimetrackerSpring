package ua.com.timetracker.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    private Integer id;

    @NotEmpty(message = "Please provide user name")
    @Pattern(regexp = "([A-Z])([a-z]{1,40})|([А-Я]([a-я]{1,40}))",
            message = "Please provide valid surname (without numbers, spaces etc.)")
    private String name;

    @NotEmpty(message = "Please provide user surname")
    @Pattern(regexp = "([A-Z])([a-z]{1,40})|([А-Я]([a-я]{1,40}))",
            message = "Please provide valid name (without numbers, spaces etc.)")
    private String surname;

    @Email(message = "Please provide a valid Email")
    @NotEmpty(message = "Please provide an email")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,15}$", message = "Password must be longer " +
            "than 5 characters and consist at least 1 upper case character, and at least 1 number")
    @NotEmpty(message = "Please provide user password")
    private String password;

    @NotNull(message = "Please provide user role")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
