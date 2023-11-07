package ru.kotb.accounting_system.excel_helper.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.excel_helper.ExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * The class that provides convert the data in the "contracts"
 * table to MS Excel format file.
 */
@Component
public class ExcelHelperImpl implements ExcelHelper {

    /**
     * The column names.
     */
    static String[] HEADERS = {
            "Name", "Type", "Planned start date",
            "Actual start date", "Planned end date", "Actual end date"
    };

    /**
     * The sheet name.
     */
    static String SHEET = "Contracts";

    /**
     * Returns the MS Excel file with all the contracts.
     *
     * @param contracts contract list
     * @return MS Excel file
     */
    //TODO: Beautify the output
    public ByteArrayInputStream convertToExcel(
            List<Contract> contracts) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIdx = 1;
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
}
