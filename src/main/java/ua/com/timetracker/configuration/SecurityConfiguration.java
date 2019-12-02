package ua.com.timetracker.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.com.timetracker.service.UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final LoginSuccessHandler handler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/",
                        "/index",
                        "/signIn",
                        "/login",
                        "/register",
                        "/signUp").permitAll()
                .antMatchers("/admin-service",
                        "/backlogs",
                        "/goals",
                        "/stories",
                        "/users",
                        "/sprints").hasAnyAuthority("ADMIN")
                .antMatchers("/developer-service",
                        "/free-stories",
                        "/developer-stories",
                        "/add-story").hasAnyAuthority("DEVELOPER")
                .antMatchers("/scrum-master-service",
                        "/project",
                        "/sprint",
                        "/create-backlog",
                        "/create-goal",
                        "/create-sprint",
                        "/create-story",
                        "/all-backlogs",
                        "/all-goals").hasAnyAuthority("SCRUM_MASTER").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?loginError=true").successHandler(handler).permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signOut"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/spring-error");
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
    }

}
