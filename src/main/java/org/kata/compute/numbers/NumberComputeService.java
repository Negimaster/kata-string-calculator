package org.kata.compute.numbers;

import org.springframework.stereotype.Service;

import java.util.OptionalInt;

@Service
public class NumberComputeService
{
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        var numberPair = splitNumbers(numbers);

        return numberPair.first() + numberPair.second().orElse(0);
    }

    private NumberPair splitNumbers(String numbers) {
        var numberSplit = numbers.split(",", -1);

        return switch (numberSplit.length) {
            case 1 -> new NumberPair(Integer.parseInt(numberSplit[0]), OptionalInt.empty());
            case 2 -> new NumberPair(Integer.parseInt(numberSplit[0]), OptionalInt.of(Integer.parseInt(numberSplit[1])));
            default -> throw new IllegalArgumentException(String.format("Argument should have at most one comma: \"%s\"", numbers));
        };
    }
}
