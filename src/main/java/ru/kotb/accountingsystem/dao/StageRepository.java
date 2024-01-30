package ru.kotb.accountingsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kotb.accountingsystem.entity.ContractStage;

import java.util.List;


@Repository
public interface StageRepository extends CommonRepository<ContractStage> {

    /**
     * Returns list of the stages of the specified contract.
     *
     * @param contractId the id of the contract
     * @return list of the stages of the specified contract
     */
    @Query("SELECT FROM CONTRACT_STAGES WHERE CONTRACT_ID = ?1")
    List<ContractStage> findByContractId(int contractId);

    /**
     * Deletes the specified stage.
     *
     * @param id the ID of the stage
     */
    void deleteById(int id);
}
