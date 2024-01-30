package ru.kotb.accountingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.kotb.accountingsystem.entity.AbstractEntity;

import javax.persistence.EntityManager;


/**
 * The common DAO that is created at the first request for it to work
 * with the specified entity. After creating the bean, you must
 * specify the class, otherwise it will not work.
 *
 * @param <E> the entity class
 */
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommonDAO<E extends AbstractEntity> extends AbstractDAO<E> {

    @Autowired
    public CommonDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
