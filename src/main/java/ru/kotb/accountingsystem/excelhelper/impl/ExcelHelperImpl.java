package ru.kotb.accountingsystem.excelhelper.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.kotb.accountingsystem.dto.ContractDTO;
import ru.kotb.accountingsystem.entity.Contract;
import ru.kotb.accountingsystem.entity.ContractStage;
import ru.kotb.accountingsystem.excelhelper.ExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * The class that provides convert the data in the "contracts"
 * table to MS Excel format file.
 */
@Component
public class ExcelHelperImpl implements ExcelHelper {

    Sheet sheet;

    /**
     * Creates the cell with given value in the specified position.
     *
     * @param rowIdx the row index
     * @param colIdx the col index
     * @param value  the value to be placed in
     */
    private void createCell(int rowIdx, int colIdx, String value) {
        Optional<Row> optRow = Optional.ofNullable(sheet.getRow(rowIdx));
        Row row = optRow.orElseGet(() -> sheet.createRow(rowIdx));
        sheet.autoSizeColumn(colIdx);
        row.createCell(colIdx).setCellValue(value);
    }

    /**
     * Creates a cell of the specified size with given value in the
     * specified position.
     */
    private void createCell(int rowNum, int colNum, String value,
                            int rowSize, int columnSize) {

        createCell(rowNum, colNum, value);
        sheet.addMergedRegion(new CellRangeAddress(
                rowNum, rowNum + rowSize - 1,
                colNum, colNum + columnSize - 1));
    }

    /**
     * Converts contracts list into the Excel spreadsheet represented
     * by ByteArrayInputStream
     *
     * @param contracts the contract list
     * @return Excel file with the contract table
     */
    public ByteArrayInputStream convertContractsToExcel(
            List<ContractDTO> contracts) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            final String[] headers = {
                    "Name", "Contract type", "Work type", "Planned start date",
                    "Actual start date", "Planned end date", "Actual end date",
                    "Amount", "Main contract"
            };

            String sheetName = "Contracts";
            int rowIdx = 0;

            sheet = workbook.createSheet(sheetName);

            for (int col = 0; col < headers.length; col++) {
                createCell(rowIdx, col, headers[col]);
            }
            rowIdx++;

            for (ContractDTO contract : contracts) {
                createContractRow(rowIdx++, contract);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }

    /**
     * Converts stages list into the Excel spreadsheet represented
     * by ByteArrayInputStream
     *
     * @param stages the contract list
     * @return Excel file with the stages table
     */
    public ByteArrayInputStream convertContractStagesToExcel(
            Contract contract, List<ContractStage> stages) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            String sheetName = "Contract stage";
            String[] twoRowsSizeHead = {"Name", "Amount"};
            String[] twoColSizeHead = {
                    "Start date", "End date", "Material cost", "Salary cost"};
            String[] subhead = {"Planned", "Actual"};
            int rowIdx = 0;
            int colIdx = 0;

            sheet = workbook.createSheet(sheetName);

            createCell(rowIdx, colIdx++, twoRowsSizeHead[0], 2, 1);
            createCell(rowIdx, colIdx++, twoRowsSizeHead[1], 2, 1);

            for (String colName : twoColSizeHead) {
                createCell(rowIdx, colIdx, colName, 1, 2);
                createCell(rowIdx + 1, colIdx, subhead[0]);
                createCell(rowIdx + 1, colIdx + 1, subhead[1]);
                colIdx += 2;
            }

            rowIdx += 2;
            for (ContractStage stage : stages) {
                createStageRow(rowIdx++, stage);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(
                    "Fail to import data to Excel file: " + e.getMessage());
        }
    }

    /**
     * Creates the row with the values of the contract attrs.
     */
    private void createContractRow(int rowIdx, ContractDTO contract) {
        int colIdx = 0;
        createCell(rowIdx, colIdx++, contract.getName());
        createCell(rowIdx, colIdx++, String.valueOf(contract.getType()));
        createCell(rowIdx, colIdx++, String.valueOf(contract.getKindOfWork()));
        createCell(rowIdx, colIdx++, String.valueOf(contract.getPlannedStartDate()));
        createCell(rowIdx, colIdx++, String.valueOf(contract.getActualStartDate()));
        createCell(rowIdx, colIdx++, String.valueOf(contract.getPlannedEndDate()));
        createCell(rowIdx, colIdx++, String.valueOf(contract.getActualEndDate()));
        createCell(rowIdx, colIdx++, String.valueOf(contract.getAmount()));
        createCell(rowIdx, colIdx, String.valueOf(contract.getMainContractName()));
    }

    /**
     * Creates the row with the values of the stage attrs.
     */
    private void createStageRow(int rowIdx, ContractStage stage) {
        int colIdx = 0;
        createCell(rowIdx, colIdx++, stage.getName());
        createCell(rowIdx, colIdx++, String.valueOf(stage.getAmount()));
        createCell(rowIdx, colIdx++, String.valueOf(stage.getPlannedStartDate()));
        createCell(rowIdx, colIdx++, String.valueOf(stage.getActualStartDate()));
        createCell(rowIdx, colIdx++, String.valueOf(stage.getPlannedEndDate()));
        createCell(rowIdx, colIdx++, String.valueOf(stage.getActualEndDate()));
        createCell(rowIdx, colIdx++, String.valueOf(stage.getPlannedMaterialCost()));
        createCell(rowIdx, colIdx++, String.valueOf(stage.getActualMaterialCost()));
        createCell(rowIdx, colIdx++, String.valueOf(stage.getPlannedSalaryCost()));
        createCell(rowIdx, colIdx, String.valueOf(stage.getActualSalaryCost()));
    }
}
