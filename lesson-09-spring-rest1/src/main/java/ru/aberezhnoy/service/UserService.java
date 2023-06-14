package ru.aberezhnoy.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.aberezhnoy.persist.User;
import ru.aberezhnoy.service.dto.UserDto;

import java.util.Optional;

@Service
public interface UserService {

    Page<UserDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort);

    Optional<UserDto> findById(Long id);

    void save(UserDto userDto);

    void deleteById(Long id);

    Optional<User> findByLogin(String login);
}
