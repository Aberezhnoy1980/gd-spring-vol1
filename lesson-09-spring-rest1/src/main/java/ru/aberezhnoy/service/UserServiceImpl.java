package ru.aberezhnoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aberezhnoy.persist.*;
import ru.aberezhnoy.service.dto.RoleDto;
import ru.aberezhnoy.service.dto.UserDto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public Page<UserDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort) {
        Specification<User> spec = Specification.where(null);
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec = spec.and(UserSpecification.nameLike(nameFilter.get()));
        }
        return userRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sort)))
                .map(user -> new UserDto(user.getId(), user.getLogin()));
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getLogin(), mapRolesDto(user)));
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getLogin(),
                encoder.encode(userDto.getPassword()),
                userDto.getRoles().stream()
                        .map(roleDto -> roleRepository.getById(roleDto.getId()))
                        .collect(Collectors.toSet()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private static Set<RoleDto> mapRolesDto(User user) {
        return user.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toSet());
    }

    private static Set<Role> mapRoles(UserDto userDto) {
        return userDto.getRoles().stream()
                .map(roleDto -> new Role(roleDto.getId(), roleDto.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
