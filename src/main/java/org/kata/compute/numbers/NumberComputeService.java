package org.kata.compute.numbers;

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
        var delimiters = "[,\n]" + bodyAndDelimiter.second().map(delimiter -> "|" + delimiter).orElse("");

        var splitNumbers = body.split(delimiters, -1);

        return Arrays.stream(splitNumbers).mapToInt(Integer::parseInt).sum();
    }

    private Pair<String, Optional<String>> getBodyAndDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            var headerAndBody = numbers.split("[\n]", 2);
            var delimiter = headerAndBody[0].substring(2);

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
