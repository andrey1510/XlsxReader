package com.xlsxreader.controllers;

import com.xlsxreader.services.ExcelReaderService;
import com.xlsxreader.services.SortingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excel-reader")
@RequiredArgsConstructor
@Schema(description = "Операции по вычислению значений.")
public class ExcelController {

    private final ExcelReaderService excelReaderService;
    private final SortingService sortingService;

    @PostMapping("/find-max-number")
    @Operation(description = "Найти максимальное по заданному порядковому номеру значение из списка чисел в .xlsx файле.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Integer.class))})})
    public ResponseEntity<Integer> getMaxNumber(
        @Parameter(description = "Путь к .xlsx файлу.")
        @RequestParam String filePath,
        @Parameter(description = "Порядковый номер максимального значения.")
        @RequestParam Integer indexNumber) {

        int[] numbers = excelReaderService.extractNumbersFromExcel(filePath);

        return ResponseEntity.ok(sortingService.findMax(numbers, indexNumber));
    }

}
