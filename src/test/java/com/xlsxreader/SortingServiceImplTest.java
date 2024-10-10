package com.xlsxreader;

import com.xlsxreader.exceptions.WrongIndexNumberException;
import com.xlsxreader.services.SortingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortingServiceImplTest {

    private SortingServiceImpl sortingService;

    @BeforeEach
    public void setUp() {
        sortingService = new SortingServiceImpl();
    }

    @Test
    public void testFindMaxWithPositiveNumbers() {
        int[] array = {0, 5, 3, 4, 1, 2};
        int result = sortingService.findMax(array, 2);
        assertEquals(4, result);
    }

    @Test
    public void testFindMaxWithNegativeNumbers() {
        int[] array = {-1, -5, -3, -4, -2, -6};
        int result = sortingService.findMax(array, 2);
        assertEquals(-2, result);
    }

    @Test
    public void testFindMaxWithSimilarNumbers() {
        int[] array = {1, 1, 1, 2};
        int result = sortingService.findMax(array, 2);
        assertEquals(1, result);
    }

    @Test
    public void testFindMaxWithSingleElement() {
        int[] array = {4};
        int result = sortingService.findMax(array, 1);
        assertEquals(4, result);
    }

    @Test
    public void testFindMaxThrowsWrongIndexNumberException() {

        int[] numbers = {1, 2, 3, 4, 5};

        assertThrows(WrongIndexNumberException.class, () -> {
            sortingService.findMax(numbers, 6);
        });
        assertThrows(WrongIndexNumberException.class, () -> {
            sortingService.findMax(numbers, 0);
        });
    }
}


