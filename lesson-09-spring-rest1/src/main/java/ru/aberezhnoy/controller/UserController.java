package ru.aberezhnoy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aberezhnoy.persist.RoleRepository;
import ru.aberezhnoy.service.UserService;
import ru.aberezhnoy.service.dto.RoleDto;
import ru.aberezhnoy.service.dto.UserDto;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "User");
    }

    @GetMapping
    public String listPage(Model model, Authentication auth,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sort") Optional<String> sort) {
        LOGGER.info("User list page requested");
        LOGGER.info("Current user {}", auth.getName());
        model.addAttribute("users", userService.findAll(
                nameFilter,
                page.orElse(1) - 1,
                size.orElse(5),
                sort.filter(s -> !s.isBlank()).orElse("id")
        ));
        return "user";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        LOGGER.info("New user page requested");

        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleRepository.findAll().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList()));
        return "user_form";
    }


    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        LOGGER.info("Edit user page requested");

        model.addAttribute("user", userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        model.addAttribute("roles", roleRepository.findAll().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList()));
        return "user_form";
    }


    @PostMapping
    public String save(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        LOGGER.info("Saving user");

        if (result.hasErrors()) {
            LOGGER.info("Error!");
            model.addAttribute("roles", roleRepository.findAll().stream()
                    .map(role -> new RoleDto(role.getId(), role.getName()))
                    .collect(Collectors.toList()));
            return "user_form";
        }

        if (!user.getPassword().equals(user.getRepeatPassword())) {
            model.addAttribute("roles", roleRepository.findAll().stream()
                    .map(role -> new RoleDto(role.getId(), role.getName()))
                    .collect(Collectors.toList()));
            result.rejectValue("password", "", "Repeated password is not correct");
            return "user_form";
        }

        userService.save(user);
        return "redirect:/user";
    }

    @Secured("ROLE_SUPER_ADMIN")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        LOGGER.info("Deleting user with id {}", id);

        userService.deleteById(id);
        return "redirect:/user";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
