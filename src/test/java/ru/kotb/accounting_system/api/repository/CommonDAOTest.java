package ru.kotb.accounting_system.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.impl.CommonDAO;

import java.util.List;


/**
 * Tests for {@code CommonDAO<E>}.
 */
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class CommonDAOTest {
    
    private final CommonDAO<TestEntity> dao;

    @Autowired
    public CommonDAOTest(CommonDAO<TestEntity> dao) {
        this.dao = dao;
        this.dao.setClass(TestEntity.class);
    }

    @Test
    public void DAOisNotNull() {
        Assertions.assertThat(dao).isNotNull();
    }

    @Test
    public void saveReturnEntityWithID() {
        TestEntity entity = new TestEntity();
        TestEntity savedEntity = dao.saveOrUpdate(entity);

        Assertions.assertThat(savedEntity.getId()).isNotNull();
    }

    @Test
    public void getAllReturnMoreThenOneEntity() {
        TestEntity entity = new TestEntity();
        TestEntity entity2 = new TestEntity();

        dao.saveOrUpdate(entity);
        dao.saveOrUpdate(entity2);

        List<TestEntity> entityList = dao.getAll();

        Assertions.assertThat(entityList).isNotNull();
        Assertions.assertThat(entityList.size()).isEqualTo(2);
    }

    @Test
    public void updateEntityReturnEntityNotNull() {
        TestEntity entity = new TestEntity();
        entity = dao.saveOrUpdate(entity);

        TestEntity entitySave = dao.get(entity.getId());
        entitySave.setName("New name");

        TestEntity updatedTestEntity = dao.saveOrUpdate(entitySave);

        Assertions.assertThat(updatedTestEntity.getName()).isNotNull();
    }

    @Test
    public void deleteReturnEntityIsNull() {
        TestEntity entity = new TestEntity();

        entity = dao.saveOrUpdate(entity);
        dao.delete(entity.getId());
        TestEntity entityReturn = dao.get(entity.getId());

        Assertions.assertThat(entityReturn).isNull();
    }
}
