package ru.kotb.accountingsystem.dao;


import org.springframework.stereotype.Repository;
import ru.kotb.accountingsystem.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends CommonRepository<User> {

    /**
     * Returns the user with the specified login.
     *
     * @param userLogin user login
     * @return the user with the specified login or null
     */
    Optional<User> findByUsername(String userLogin);
}
