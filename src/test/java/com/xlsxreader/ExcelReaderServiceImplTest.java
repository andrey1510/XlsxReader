package com.xlsxreader;

import com.xlsxreader.exceptions.EmptyXlsxFileException;
import com.xlsxreader.exceptions.XlsxFileNotFoundException;
import com.xlsxreader.services.ExcelReaderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExcelReaderServiceImplTest {

    private ExcelReaderServiceImpl excelReaderService;

    @BeforeEach
    void setUp() {
        excelReaderService = new ExcelReaderServiceImpl();
    }

    @Test
    void testExtractNumbersFromExcel() {

        String filePath = "src/test/java/com/xlsxreader/resources/testData.xlsx";

        int[] expectedNumbers = {6, 32, 1, 8, 4};
        int[] actualNumbers = excelReaderService.extractNumbersFromExcel(filePath);

        assertArrayEquals(expectedNumbers, actualNumbers);
    }


    @Test
    void testEmptyExcel() {

        String filePath = "src/test/java/com/xlsxreader/resources/testDataEmpty.xlsx";

        assertThrows(EmptyXlsxFileException.class, () -> {
            excelReaderService.extractNumbersFromExcel(filePath);
        });
    }

    @Test
    void testNoFile() {

        String filePath = "src/test/java/com/xlsxreader/resources/wrongTitle.xlsx";

        assertThrows(XlsxFileNotFoundException.class, () -> {
            excelReaderService.extractNumbersFromExcel(filePath);
        });
    }

}
