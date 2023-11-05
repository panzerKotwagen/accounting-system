package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.Role;

import java.util.Optional;


public interface RoleDAO extends CommonDAO<Role> {

    Optional<Role> findByAuthority(String authority);
}
