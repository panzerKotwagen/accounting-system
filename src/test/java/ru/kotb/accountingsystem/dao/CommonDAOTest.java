package ru.kotb.accountingsystem.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.configuration.TestConfig;
import ru.kotb.accountingsystem.entity.TestEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;


/**
 * Tests for {@code CommonDAO<E>}.
 */
@DataJpaTest
@Import(TestConfig.class)
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
        entity = dao.findById(entity.getId()).get();

        Assertions.assertThat(entity).isNotNull();
    }

    @Test
    public void findByIdNonExistedEntity() {
        Optional<TestEntity> entity = dao.findById(0);

        Assertions.assertThat(entity).isEmpty();
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
    public void saveTestEntityTwoTimesReturnsDifferentEntities() {
        TestEntity entity = new TestEntity("Test");

        TestEntity savedTestEntity1 = dao.save(entity);
        TestEntity savedTestEntity2 = dao.save(entity);

        Assertions.assertThat(savedTestEntity1).isNotEqualTo(savedTestEntity2);
    }

    @Test
    public void findAllReturnEmptyList() {
        List<TestEntity> entityList = dao.findAll();

        Assertions.assertThat(entityList.size()).isEqualTo(0);
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

        TestEntity entitySave = dao.findById(entity.getId()).get();
        entitySave.setName("New name");

        TestEntity updatedTestEntity = dao.save(entitySave);

        Assertions.assertThat(updatedTestEntity.getName()).isNotNull();
        Assertions.assertThat(updatedTestEntity.getId())
                .isEqualTo(entitySave.getId());
        Assertions.assertThat(updatedTestEntity.getName())
                .isEqualTo("New name");
    }

    @Test
    public void deleteReturnEntityIsNull() {
        TestEntity entity = new TestEntity();

        TestEntity savedEntity = dao.save(entity);

        assertAll(() -> dao.deleteById(savedEntity.getId()));
    }

    @Test
    public void deleteNonExistedEntity() {
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchElementException.class,
                () -> dao.deleteById(-100));
    }
}
