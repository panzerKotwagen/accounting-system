package ru.kotb.accounting_system.excel_helper.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.excel_helper.ExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * The class that provides convert the data in the "contracts"
 * table to MS Excel format file.
 */
//TODO: Add comments
@Component
public class ExcelHelperImpl implements ExcelHelper {


    //TODO: Beautify the output
    /**
     * Converts contracts list into the Excel spreadsheet represented
     * by ByteArrayInputStream
     *
     * @param contracts the contract list
     * @return Excel file with the contract table
     */
    public ByteArrayInputStream convertContractsToExcel(
            List<Contract> contracts) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            String[] HEADERS = {
                    "Name", "Type", "Planned start date",
                    "Actual start date", "Planned end date", "Actual end date"
            };
            String SHEET = "Contracts";
            int rowIdx = 0;

            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(rowIdx++);
            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            for (Contract contract : contracts) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(
                        contract.getName());

                row.createCell(1).setCellValue(
                        contract.getContractType().getName());

                row.createCell(2).setCellValue(String.valueOf(
                        contract.getPlannedStartDate()));

                row.createCell(3).setCellValue(String.valueOf(
                        contract.getActualStartDate()));

                row.createCell(4).setCellValue(String.valueOf(
                        contract.getPlannedEndDate()));

                row.createCell(5).setCellValue(String.valueOf(
                        contract.getActualEndDate()));

                row.createCell(6).setCellValue(
                        contract.getAmount());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }

    //TODO: Beautify the output and refactor
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

            String SHEET = "Contract stage";
            String[] twoRowsHeaders = {"Name", "Amount"};
            String[] firstRowHeaders = {
                    "Start date", "End date", "Material cost", "Salary cost"};
            String[] secondRowHeaders = {"Planned", "Actual"};
            int rowIdx = 0;

            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(rowIdx++);
            headerRow.createCell(0).setCellValue(contract.getName());
            sheet.addMergedRegion(CellRangeAddress.valueOf("A1:J1"));

            headerRow = sheet.createRow(rowIdx++);
            for (int col = 0; col < 2; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(twoRowsHeaders[col]);
            }

            for (int col = 0; col < 4; col++) {
                Cell cell = headerRow.createCell(col * 2 + 2);
                sheet.addMergedRegion(
                        new CellRangeAddress(
                                rowIdx - 1, rowIdx - 1,
                                col * 2 + 2, col * 2 + 3));
                cell.setCellValue(firstRowHeaders[col]);
            }

            headerRow = sheet.createRow(rowIdx++);
            for (int col = 0; col < 8; col++) {
                Cell cell = headerRow.createCell(col + 2);
                cell.setCellValue(secondRowHeaders[col % 2]);
            }

            sheet.addMergedRegion(new CellRangeAddress(
                    1, 2, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(
                    1, 2, 1, 1));

            for (ContractStage stage : stages) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(stage.getName());

                row.createCell(1).setCellValue(stage.getAmount());

                row.createCell(2).setCellValue(String.valueOf(
                        stage.getPlannedStartDate()));

                row.createCell(3).setCellValue(String.valueOf(
                        stage.getActualStartDate()));

                row.createCell(4).setCellValue(String.valueOf(
                        stage.getPlannedEndDate()));

                row.createCell(5).setCellValue(String.valueOf(
                        stage.getActualEndDate()));

                row.createCell(6).setCellValue(stage.getPlannedMaterialCost());

                row.createCell(7).setCellValue(stage.getActualMaterialCost());

                row.createCell(8).setCellValue(stage.getPlannedSalaryCost());

                row.createCell(9).setCellValue(stage.getActualSalaryCost());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(
                    "Fail to import data to Excel file: " + e.getMessage());
        }
    }
}
