package ru.kotb.accountingsystem.repository;

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
//    @Query("SELECT FROM CONTRACT_STAGES WHERE CONTRACT_ID = ?1")
    List<ContractStage> findByContractId(int contractId);

    /**
     * Deletes the specified stage.
     *
     * @param id the ID of the stage
     */
    default void deleteById(int id) {
        ContractStage stage = findById(id).get();
        stage.getContract().getContractStages().remove(stage);
    }
}
