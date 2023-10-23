package ru.kotb.accounting_system.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.GenericDAO;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDAOImpl<T> extends AbstractDAO<T> implements GenericDAO<T> {

    @Autowired
    public GenericDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
