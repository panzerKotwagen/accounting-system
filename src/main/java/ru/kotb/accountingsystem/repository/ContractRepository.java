package ru.kotb.accountingsystem.repository;


import org.springframework.stereotype.Repository;
import ru.kotb.accountingsystem.entity.Contract;

import java.sql.Date;
import java.util.List;


@Repository
public interface ContractRepository extends CommonRepository<Contract> {

    /**
     * Returns the list of contracts for the specified period.
     *
     * @param start planned start date
     * @param end   planned end date
     */
    List<Contract> findByPlannedStartDateGreaterThanEqualAndPlannedEndDateLessThanEqual(Date start, Date end);
}
