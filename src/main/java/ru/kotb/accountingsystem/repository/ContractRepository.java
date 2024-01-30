package ru.kotb.accountingsystem.repository;


import org.springframework.stereotype.Repository;
import ru.kotb.accountingsystem.entity.Contract;

import java.sql.Date;
import java.util.List;


@Repository
public interface ContractRepository extends CommonRepository<Contract> {

    List<Contract> findByPlannedStartDateGreaterThanEqualAndPlannedEndDateLessThanEqual(Date start, Date end);
}
