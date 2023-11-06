package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.RoleDAO;
import ru.kotb.accounting_system.entity.Role;

import javax.persistence.EntityManager;
import java.util.Optional;


@Repository
public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the SessionFactory object
     */
    @Autowired
    public RoleDAOImpl(EntityManager sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        Session session = sessionFactory.unwrap(Session.class);

        Query<Role> query = session.createQuery("from Role " +
                "where authority = :authority", Role.class);
        query.setParameter("authority", authority);

        return Optional.ofNullable(query.uniqueResult());
    }
}
