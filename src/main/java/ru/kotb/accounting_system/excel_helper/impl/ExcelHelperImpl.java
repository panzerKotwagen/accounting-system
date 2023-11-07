package ru.kotb.accounting_system.excel_helper.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.excel_helper.ExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


/**
 *
 */
public class ExcelHelperImpl implements ExcelHelper {

    public static String TYPE = "application/" +
            "vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = {
            "Name", "Type", "Planned start",
            "Actual start", "Planned end", "Actual end"
    };
    static String SHEET = "Contracts";

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
            for (Contract tutorial : contracts) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(tutorial.getName());
                row.createCell(1).setCellValue(tutorial.getContractType().getName());
                row.createCell(2).setCellValue(tutorial.getPlannedStartDate());
                row.createCell(3).setCellValue(tutorial.getActualStartDate());
                row.createCell(4).setCellValue(tutorial.getPlannedEndDate());
                row.createCell(5).setCellValue(tutorial.getActualEndDate());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }
}
