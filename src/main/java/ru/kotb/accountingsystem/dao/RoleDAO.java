package ru.kotb.accountingsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;


/**
 * The DAO to work with the {@code Role} entity.
 */
@Repository
public class RoleDAO extends AbstractDAO<Role> {

    @Autowired
    public RoleDAO(EntityManager entityManager) {
        super(entityManager);
        setClass(Role.class);
    }

    /**
     * Returns the role with specified name.
     *
     * @param authority the name of the role
     * @return the role with specified name
     */
    @Transactional
    public Optional<Role> findByAuthority(String authority) {
        Query query = entityManager.createQuery("from Role " +
                "where authority = :authority", Role.class);
        query.setParameter("authority", authority);

        return query.getResultList().stream().findFirst();
    }
}
