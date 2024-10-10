package com.xlsxreader.services;

import com.xlsxreader.exceptions.EmptyXlsxFileException;
import com.xlsxreader.exceptions.XlsxFileNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {

    private final String XLSX_FILE_NOT_FOUND = "Неправильный путь к файлу.";
    private final String EMPTY_XLSX_FILE = "В первой ячейке первого листа .xlsx файла нет числа.";

    @Override
    public int[] extractNumbersFromExcel(String filePath) {

        ArrayList<Integer> numbersList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) numbersList.add((int) cell.getNumericCellValue());
            }
        } catch (IOException e) {
            throw new XlsxFileNotFoundException(XLSX_FILE_NOT_FOUND);
        }

        if (numbersList.isEmpty()) {
            throw new EmptyXlsxFileException(EMPTY_XLSX_FILE);
        }

        int[] numbers = new int[numbersList.size()];
        for (int i = 0; i < numbersList.size(); i++) {
            numbers[i] = numbersList.get(i);
        }
        return numbers;
    }

}

