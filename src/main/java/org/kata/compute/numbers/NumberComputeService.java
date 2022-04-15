package org.kata.compute.numbers;

import org.kata.compute.numbers.exception.NegativeNumbersForbiddenException;
import org.kata.compute.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class NumberComputeService
{
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        var bodyAndDelimiter = getBodyAndDelimiter(numbers);

        var body = bodyAndDelimiter.first();
        var delimiters = "[,\n]" + bodyAndDelimiter.second().map(delimiter -> "|\\Q" + delimiter + "\\E").orElse("");

        var splitNumbers = Arrays.stream(body.split(delimiters, -1)).mapToInt(Integer::parseInt).toArray();

        var negativeNumbers = Arrays.stream(splitNumbers).filter(n -> n < 0).toArray();
        if (negativeNumbers.length != 0) {
            throw new NegativeNumbersForbiddenException(String.format("negatives not allowed: %s", Arrays.toString(negativeNumbers)));
        }

        return Arrays.stream(splitNumbers).filter(n -> n <= 1000).sum();
    }

    private Pair<String, Optional<String>> getBodyAndDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            var headerAndBody = numbers.split("[\n]", 2);
            var delimiterGroup = headerAndBody[0].substring(2);

            if (delimiterGroup.isEmpty()) {
                throw new IllegalArgumentException("Delimiter suggestion should not be empty");
            }

            String delimiter;
            if (delimiterGroup.charAt(0) == '[' && delimiterGroup.charAt(delimiterGroup.length() - 1) == ']') {
                delimiter = delimiterGroup.substring(1, delimiterGroup.length() - 1);
            } else {
                delimiter = delimiterGroup;
            }

            if (delimiter.isEmpty()) {
                throw new IllegalArgumentException("Delimiter suggestion should not be empty");
            }

            var body = headerAndBody[1];

            return new Pair<>(body, Optional.of(delimiter));
        } else {
            return new Pair<>(numbers, Optional.empty());
        }
    }
}
