package ru.kotb.accounting_system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.entity.AbstractEntity;

import javax.persistence.EntityManager;


//TODO: Add comments
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommonDAO<E extends AbstractEntity> extends AbstractDAO<E> {

    /**
     * Creates the DAO and binds it with the entityManager bean.
     *
     * @param entityManager the EntityManager bean
     */
    @Autowired
    public CommonDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
