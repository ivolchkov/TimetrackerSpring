package project.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.domain.User;
import project.entity.UserEntity;
import project.exception.AlreadyRegisteredException;
import project.exception.EntityNotFoundException;
import project.exception.InvalidPaginatingException;
import project.exception.InvalidRegistrationException;
import project.repository.UserRepository;
import project.service.UserService;
import project.service.mapper.UserMapper;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;

    @Override
    public User register(User user) {
        if (Objects.isNull(user)) {
            log.warn("User is not valid");
            throw new InvalidRegistrationException("User is not valid");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.warn("User is already registered by this e-mail");
            throw new AlreadyRegisteredException("User is already registered by this e-mail");
        }

        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);

        UserEntity entity = userRepository.save(mapper.mapUserToUserEntity(user));

        return mapper.mapUserEntityToUser(entity);
    }

    @Override
    public User login(String email, String password) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);

        if (!entity.isPresent()) {
            log.warn("There is no user with this e-mail");
            throw new EntityNotFoundException("There is no user with this e-mail");
        }

        if (encoder.matches(password, entity.get().getPassword())) {
            return mapper.mapUserEntityToUser(entity.get());
        }

        log.warn("Incorrect password");
        throw new EntityNotFoundException("Incorrect password");
    }

    @Override
    @Secured("ADMIN")
    public List<User> findAll(int currentPage, int recordsPerPage) {
        if (currentPage < 0 || recordsPerPage < 0) {
            log.warn("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<UserEntity> result = userRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return userRepository.count();
    }
}
