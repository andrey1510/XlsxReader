package com.xlsxreader.services;

import com.xlsxreader.exceptions.WrongIndexNumberException;
import org.springframework.stereotype.Service;

@Service
public class SortingServiceImpl implements SortingService {

    private final String WRONG_INDEX_NUM = "Порядковый номер не может быть меньше 1 и больше количества чисел в списке";

    @Override
    public int findMax(int[] numbers, int indexNumber) {

        if(indexNumber > numbers.length || indexNumber < 1) {
            throw new WrongIndexNumberException(WRONG_INDEX_NUM);
        }

        int[] numbersSorted = sort(numbers);

        return numbersSorted[numbers.length - indexNumber];
    }

    public static int[] sort(int[] numbers) {
        int n = numbers.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)  break;
        }

        return numbers;
    }

}

