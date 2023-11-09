package ru.kotb.accounting_system.excel_helper;

import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * The interface that provides convert the data in the "contracts"
 * table to MS Excel format file.
 */
public interface ExcelHelper {
    ByteArrayInputStream convertContractsToExcel(
            List<Contract> contracts);

    ByteArrayInputStream convertContractStagesToExcel(
            Contract contract, List<ContractStage> stages);
}
