package ru.kotb.accounting_system.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.api.entity.TestEntity;
import ru.kotb.accounting_system.dao.impl.CommonDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;


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
    public void findByIdReturnEntity() {
        TestEntity entity = new TestEntity();

        entity = dao.save(entity);
        entity = dao.findById(entity.getId());

        Assertions.assertThat(entity).isNotNull();
    }

    @Test
    public void findByIdNonExistedEntity() {
        TestEntity entity = dao.findById(0);

        Assertions.assertThat(entity).isNull();
    }

    @Test
    public void findAllReturnMoreThenOneEntity() {
        TestEntity entity = new TestEntity();
        TestEntity entity2 = new TestEntity();

        dao.save(entity);
        dao.save(entity2);

        List<TestEntity> entityList = dao.findAll();

        Assertions.assertThat(entityList).isNotNull();
        Assertions.assertThat(entityList.size()).isEqualTo(2);
    }

    @Test
    public void saveReturnEntityWithID() {
        TestEntity entity = new TestEntity();

        TestEntity savedEntity = dao.save(entity);

        Assertions.assertThat(savedEntity.getId()).isNotNull();
    }
    @Test
    public void updateEntityReturnEntityNotNull() {
        TestEntity entity = new TestEntity();
        entity = dao.save(entity);

        TestEntity entitySave = dao.findById(entity.getId());
        entitySave.setName("New name");

        TestEntity updatedTestEntity = dao.save(entitySave);

        Assertions.assertThat(updatedTestEntity.getName()).isNotNull();
        Assertions.assertThat(updatedTestEntity.getName())
                .isEqualTo("New name");
    }

    @Test
    public void deleteReturnEntityIsNull() {
        TestEntity entity = new TestEntity();

        dao.save(entity);

        assertAll(() -> dao.delete(1));
    }

    @Test
    public void deleteNonExistedEntity() {
        org.junit.jupiter.api.Assertions.assertThrows(
                InvalidDataAccessApiUsageException.class,
                () -> dao.delete(-100));
    }
}
