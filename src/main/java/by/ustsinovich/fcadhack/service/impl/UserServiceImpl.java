package by.ustsinovich.fcadhack.service.impl;

import by.ustsinovich.fcadhack.dto.UserDto;
import by.ustsinovich.fcadhack.dto.request.RegisterRequest;
import by.ustsinovich.fcadhack.entity.User;
import by.ustsinovich.fcadhack.enums.UserRole;
import by.ustsinovich.fcadhack.enums.UserSort;
import by.ustsinovich.fcadhack.exception.UserNotFoundException;
import by.ustsinovich.fcadhack.filter.UserFilter;
import by.ustsinovich.fcadhack.repository.UserRepository;
import by.ustsinovich.fcadhack.service.UserService;
import by.ustsinovich.fcadhack.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(RegisterRequest registerRequest) {
        logger.info("Creating new user with email: {}", registerRequest.getEmail());
        User user = User
                .builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .patronymic(registerRequest.getPatronymic())
                .lastName(registerRequest.getLastName())
                .role(UserRole.ROLE_USER)
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .build();

        return userRepository.save(user);
    }

    @Override
    public Page<User> getAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    ) {
        logger.info("Getting all users with filter: {}", filter);
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<User> specification = UserSpecification.filterBy(filter);

        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User getUserById(Long id) {
        logger.info("Getting user by ID: {}", id);
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        logger.info("Deleting user with ID: {}", id);
        User user = getUserById(id);

        userRepository.delete(user);
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserDto userDto) {
        logger.info("Updating user with ID: {}", id);
        User user = getUserById(id);

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setPatronymic(userDto.getPatronymic());
        user.setLastName(userDto.getLastName());

        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Getting user by email: {}", email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

}
