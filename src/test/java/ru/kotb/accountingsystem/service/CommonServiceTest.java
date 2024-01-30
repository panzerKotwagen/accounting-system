package ru.kotb.accountingsystem.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kotb.accountingsystem.entity.TestEntity;
import ru.kotb.accountingsystem.repository.CommonDAO;
import ru.kotb.accountingsystem.exception.handling.NoSuchEntityException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CommonServiceTest {

    @Mock
    private CommonDAO<TestEntity> dao;

    @InjectMocks
    private TestService service;

    
    @Test
    public void createTestEntityReturnsTestEntity() {
        TestEntity entity = new TestEntity("Test");

        when(dao.save(Mockito.any(TestEntity.class))).thenReturn(entity);

        TestEntity savedTestEntity = service.save(entity);

        Assertions.assertThat(savedTestEntity).isNotNull();
    }
    
    @Test
    public void findByIdReturnTestEntity() {
        int entityId = 1;
        TestEntity entity = new TestEntity("Test");
        
        when(dao.findById(entityId)).thenReturn(Optional.of(entity));

        TestEntity entityReturn = service.getById(entityId);

        Assertions.assertThat(entityReturn).isNotNull();
    }

    @Test
    public void findByNonExistedIdThrowsException() {
        int entityId = -1;

        when(dao.findById(entityId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchEntityException.class,
                () -> service.getById(entityId));
    }

    @Test
    public void updateTestEntityReturnTestEntity() {
        String newName = "Test123";
        TestEntity prevUpdate = new TestEntity("Test");
        prevUpdate.setName(newName);

        when(dao.save(prevUpdate)).thenReturn(prevUpdate);

        TestEntity updateReturn = service.save(prevUpdate);

        Assertions.assertThat(updateReturn).isNotNull();
        Assertions.assertThat(updateReturn.getName())
                .isEqualTo(newName);
    }

    @Test
    public void deleteNonExistedEntityThrowsException() {
        doThrow(NoSuchElementException.class)
                .when(dao).deleteById(Mockito.anyInt());

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchEntityException.class,
                () -> service.deleteById(-100));
    }
}
