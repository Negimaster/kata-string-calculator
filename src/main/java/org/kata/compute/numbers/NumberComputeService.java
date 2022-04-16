package org.kata.compute.numbers;

import org.kata.compute.numbers.exception.NegativeNumbersForbiddenException;
import org.kata.compute.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class NumberComputeService
{
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        var bodyAndDelimiters = getBodyAndDelimiters(numbers);

        var body = bodyAndDelimiters.first();
        var delimiters = "[,\n]" +
                bodyAndDelimiters.second()
                .stream()
                .map(delimiter -> "|\\Q" + delimiter + "\\E") // escape special characters for regex
                .collect(Collectors.joining());

        var splitNumbers = Arrays.stream(body.split(delimiters, -1)).mapToInt(Integer::parseInt).toArray();

        var negativeNumbers = Arrays.stream(splitNumbers).filter(n -> n < 0).toArray();
        if (negativeNumbers.length != 0) {
            throw new NegativeNumbersForbiddenException(String.format("negatives not allowed: %s", Arrays.toString(negativeNumbers)));
        }

        return Arrays.stream(splitNumbers).filter(n -> n <= 1000).sum();
    }

    private Pair<String, List<String>> getBodyAndDelimiters(String numbers) {
        if (numbers.startsWith("//")) {
            var headerAndBody = numbers.split("[\n]", 2);
            var delimitersLine = headerAndBody[0].substring(2);
            var body = headerAndBody[1];

            if (delimitersLine.isEmpty()) {
                throw new IllegalArgumentException("Delimiter suggestion should not be empty");
            }

            // One single character delimiter
            if (delimitersLine.length() == 1) {
                return new Pair<>(body, Collections.singletonList(delimitersLine));
            } else {
                Pattern delimiterPattern = Pattern.compile("\\[([^]]*)]");
                Matcher delimitersMatcher = delimiterPattern.matcher(delimitersLine);

                var delimiters = delimitersMatcher.results().map(match -> match.group(1)).toList();

                if (delimiters.isEmpty() || delimiters.stream().anyMatch(String::isEmpty)) {
                    throw new IllegalArgumentException("Delimiter suggestion should not be empty");
                }

                return new Pair<>(body, delimiters);
            }
        } else {
            return new Pair<>(numbers, emptyList());
        }
    }
}
