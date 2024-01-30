package ru.kotb.accountingsystem.excelhelper;

import ru.kotb.accountingsystem.dto.ContractDTO;
import ru.kotb.accountingsystem.entity.Contract;
import ru.kotb.accountingsystem.entity.ContractStage;

import java.io.ByteArrayInputStream;
import java.util.List;


/**
 * The interface that provides convert the data in the "contracts"
 * and "stages" tables to MS Excel format file.
 */
public interface ExcelHelper {

    /**
     * Converts contracts list into the Excel spreadsheet represented
     * by ByteArrayInputStream
     *
     * @param contracts the contract list
     * @return Excel file with the contract table
     */
    ByteArrayInputStream convertContractsToExcel(
            List<ContractDTO> contracts);

    /**
     * Converts stages list into the Excel spreadsheet represented
     * by ByteArrayInputStream
     *
     * @param stages the contract list
     * @return Excel file with the stages table
     */
    ByteArrayInputStream convertContractStagesToExcel(
            Contract contract, List<ContractStage> stages);
}
