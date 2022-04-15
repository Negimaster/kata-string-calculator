package org.kata.compute.numbers;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.OptionalInt;

@Service
public class NumberComputeService
{
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        var splitNumbers = numbers.split(",", -1);

        return Arrays.stream(splitNumbers).mapToInt(Integer::parseInt).sum();
    }
}
