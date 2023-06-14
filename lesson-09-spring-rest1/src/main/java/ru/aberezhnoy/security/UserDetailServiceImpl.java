package ru.aberezhnoy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aberezhnoy.service.UserService;

import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByLogin(username)
                .map(user -> new User(
                        user.getLogin(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(r -> new SimpleGrantedAuthority(r.getName()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
