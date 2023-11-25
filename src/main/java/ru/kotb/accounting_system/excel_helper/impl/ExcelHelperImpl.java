package ru.kotb.accounting_system.excel_helper.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.dto.ContractDTO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.excel_helper.ExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    private void create_cell(int rowIdx, int colIdx, String value) {
        Optional<Row> optRow = Optional.ofNullable(sheet.getRow(rowIdx));
        Row row = optRow.orElseGet(() -> sheet.createRow(rowIdx));
        sheet.autoSizeColumn(colIdx);
        row.createCell(colIdx).setCellValue(value);
    }

    /**
     * Creates a cell of the specified size with given value in the
     * specified position.
     */
    private void create_cell(int rowNum, int colNum, String value,
                             int rowSize, int columnSize) {

        create_cell(rowNum, colNum, value);
        sheet.addMergedRegion(new CellRangeAddress(
                rowNum, rowNum + rowSize - 1,
                colNum, colNum + columnSize - 1));
    }

    private void create_row(int rowIdx, int startColIdx, List<String> values) {
        for (String s : values) {
            create_cell(rowIdx, startColIdx++, s);
        }
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
            List<List<String>> contractAttrLists = contracts.stream()
                    .map(ContractDTO::getAttributesAsStringList)
                    .collect(Collectors.toList());
            int rowIdx = 0;

            sheet = workbook.createSheet(sheetName);

            for (int col = 0; col < headers.length; col++) {
                create_cell(rowIdx, col, headers[col]);
            }
            rowIdx++;

            for (List<String> contractAttrs : contractAttrLists) {
                create_row(rowIdx++, 0, contractAttrs);
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

            create_cell(rowIdx, colIdx++, twoRowsSizeHead[0], 2, 1);
            create_cell(rowIdx, colIdx++, twoRowsSizeHead[1], 2, 1);

            for (String colName : twoColSizeHead) {
                create_cell(rowIdx, colIdx, colName, 1, 2);
                create_cell(rowIdx + 1, colIdx, subhead[0]);
                create_cell(rowIdx + 1, colIdx + 1, subhead[1]);
                colIdx += 2;
            }

            rowIdx += 2;
            for (ContractStage stage : stages) {
                create_stage_row(rowIdx++, stage);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(
                    "Fail to import data to Excel file: " + e.getMessage());
        }
    }

    /**
     * Creates the row with the values of the stage attrs.
     */
    private void create_stage_row(int rowIdx, ContractStage stage) {
        int colIdx = 0;
        create_cell(rowIdx, colIdx++, stage.getName());
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getAmount()));
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getPlannedStartDate()));
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getActualStartDate()));
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getPlannedEndDate()));
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getActualEndDate()));
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getPlannedMaterialCost()));
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getActualMaterialCost()));
        create_cell(rowIdx, colIdx++, String.valueOf(stage.getPlannedSalaryCost()));
        create_cell(rowIdx, colIdx, String.valueOf(stage.getActualSalaryCost()));
    }
}
