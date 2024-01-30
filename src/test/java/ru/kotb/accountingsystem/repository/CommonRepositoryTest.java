package ru.kotb.accountingsystem.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.entity.TestEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;


/**
 * Tests for {@code CommonRepository<E>}.
 */
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class CommonRepositoryTest {
    
    private final CommonRepository<TestEntity> rep;

    @Autowired
    public CommonRepositoryTest(CommonRepository<TestEntity> rep) {
        this.rep = rep;
    }

    @Test
    public void repositoryisNotNull() {
        Assertions.assertThat(rep).isNotNull();
    }

    @Test
    public void findByIdReturnEntity() {
        TestEntity entity = new TestEntity();

        entity = rep.save(entity);
        entity = rep.findById(entity.getId()).get();

        Assertions.assertThat(entity).isNotNull();
    }

    @Test
    public void findByIdNonExistedEntity() {
        Optional<TestEntity> entity = rep.findById(0);

        Assertions.assertThat(entity).isEmpty();
    }

    @Test
    public void findAllReturnMoreThenOneEntity() {
        TestEntity entity = new TestEntity();
        TestEntity entity2 = new TestEntity();

        rep.save(entity);
        rep.save(entity2);

        List<TestEntity> entityList = rep.findAll();

        Assertions.assertThat(entityList).isNotNull();
        Assertions.assertThat(entityList.size()).isEqualTo(2);
    }

    @Test
    public void saveSameEntityTwoTimesReturnsSameEntity() {
        TestEntity entity = new TestEntity("Test");

        TestEntity savedTestEntity1 = rep.save(entity);
        TestEntity savedTestEntity2 = rep.save(entity);

        Assertions.assertThat(savedTestEntity1).isEqualTo(savedTestEntity2);
    }

    @Test
    public void findAllReturnEmptyList() {
        List<TestEntity> entityList = rep.findAll();

        Assertions.assertThat(entityList.size()).isEqualTo(0);
    }

    @Test
    public void saveReturnEntityWithID() {
        TestEntity entity = new TestEntity();

        TestEntity savedEntity = rep.save(entity);

        Assertions.assertThat(savedEntity.getId()).isNotNull();
    }

    @Test
    public void updateEntityReturnEntityNotNull() {
        TestEntity entity = new TestEntity();
        entity = rep.save(entity);

        TestEntity entitySave = rep.findById(entity.getId()).get();
        entitySave.setName("New name");

        TestEntity updatedTestEntity = rep.save(entitySave);

        Assertions.assertThat(updatedTestEntity.getName()).isNotNull();
        Assertions.assertThat(updatedTestEntity.getId())
                .isEqualTo(entitySave.getId());
        Assertions.assertThat(updatedTestEntity.getName())
                .isEqualTo("New name");
    }

    @Test
    public void deleteReturnEntityIsNull() {
        TestEntity entity = new TestEntity();

        TestEntity savedEntity = rep.save(entity);

        assertAll(() -> rep.deleteById(savedEntity.getId()));
    }

    @Test
    public void deleteNonExistedEntity() {
        org.junit.jupiter.api.Assertions.assertThrows(
                EmptyResultDataAccessException.class,
                () -> rep.deleteById(-100));
    }
}
