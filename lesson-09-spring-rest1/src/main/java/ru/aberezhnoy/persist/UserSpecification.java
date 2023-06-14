package ru.aberezhnoy.persist;

import org.springframework.data.jpa.domain.Specification;

public final class UserSpecification {
    public static Specification<User> nameLike(String pattern) {
        return (root, query, builder) -> builder.like(root.get("login"), "%" + pattern + "%");
    }
}
