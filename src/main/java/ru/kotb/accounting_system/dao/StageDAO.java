package ru.kotb.accounting_system.dao;

import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.entity.ContractStage;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


/**
 * The DAO to work with the {@code ContractStage} entity.
 */
@Repository
public class StageDAO extends AbstractDAO<ContractStage> {

    public StageDAO(EntityManager entityManager) {
        super(entityManager);
        setClass(ContractStage.class);
    }

    /**
     * Returns list of the stages of the specified contract.
     *
     * @param contractId the id of the contract
     * @return list of the stages of the specified contract
     */
    public List<ContractStage> findAllByContractId(int contractId) {
        Query query = entityManager.createQuery(" FROM ContractStage " +
                "where contract.id = :contractId");

        query.setParameter("contractId", contractId);

        return (List<ContractStage>) query.getResultList();
    }

    /**
     * Deletes the specified stage.
     *
     * @param id the ID of the stage
     */
    @Override
    public void deleteById(int id) {
        ContractStage stage = findById(id).get();
        stage.getContract().getContractStages().remove(stage);
        super.deleteById(id);
    }
}
