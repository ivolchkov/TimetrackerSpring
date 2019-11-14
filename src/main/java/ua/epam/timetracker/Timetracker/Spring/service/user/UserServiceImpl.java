package ua.epam.timetracker.Timetracker.Spring.service.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.backlog.Backlog;
import ua.epam.timetracker.Timetracker.Spring.domain.user.User;
import ua.epam.timetracker.Timetracker.Spring.entity.user.UserEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.*;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.UserMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

//    private final UserDao userDao;
    private final UserMapper mapper;

//    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder, UserMapper mapper, Validator<User> validator) {
//        this.userDao = userDao;
//        this.encoder = encoder;
//        this.mapper = mapper;
//        this.validator = validator;
//    }
    @Autowired
public UserServiceImpl(UserMapper mapper) {
    this.mapper = mapper;
}

    @Override
    public boolean register(User user) {
//        validator.validate(user);
//
//        if (userDao.findByEmail(user.getEmail()).isPresent()) {
//            LOGGER.warn("User is already registered by this e-mail");
//            throw new AlreadyRegisteredException("User is already registered by this e-mail");
//        }
//
//        String encoded = encoder.encode(user.getPassword()).
//                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
//        User userWithEncodedPass = new User(user, encoded);
//
//        return userDao.save(mapper.mapUserToUserEntity(userWithEncodedPass));
        return true;
    }

    @Override
    public User login(String email, String password) {
//        String encodedPassword = encoder.encode(password).
//                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
//        Optional<UserEntity> entity = userDao.findByEmail(email);
//
//        if (!entity.isPresent()) {
//            LOGGER.warn("There is no user with this e-mail");
//            throw new EntityNotFoundException("There is no user with this e-mail");
//        } else {
//            if (entity.get().getPassword().equals(encodedPassword)) {
//                return mapper.mapUserEntityToUser(entity.get());
//            } else {
//                LOGGER.warn("Incorrect password");
//                throw new EntityNotFoundException("Incorrect password");
//            }
//        }
        return User.builder().id(1).build();
    }

    @Override
    public List<User> findTeam(Integer backlogId, Integer currentPage, Integer recordsPerPage) {
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//        List<UserEntity> result = userDao.findByBacklog(backlogId, offset, recordsPerPage);
//
//        return listMapping(result);
        return null;
    }

    @Override
    public List<User> findAll(Integer currentPage, Integer recordsPerPage) {
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//        List<UserEntity> result = userDao.findAll(offset, recordsPerPage);
//
//        return listMapping(result);
        return Collections.singletonList(User.builder().id(1).build());
    }

    @Override
    public void addProjectToUser(User user, Backlog backlog) {
//        if (Objects.isNull(user) || Objects.isNull(backlog) ) {
//            LOGGER.warn("Invalid user updating");
//            throw new InvalidEntityUpdating("Invalid user updating");
//        }
//
//        userDao.updateBacklogId(mapper.mapUserToUserEntity(user, backlog));
    }

    @Override
    public Integer showNumberOfRows() {
//
//    return userDao.findAmountOfRows();
        return 10;
    }

    private List<User> listMapping(List<UserEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    private void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (currentPage == 0 || recordsPerPage == 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }
    }
}
