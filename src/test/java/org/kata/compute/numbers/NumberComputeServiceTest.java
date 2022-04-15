package org.kata.compute.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;

class NumberComputeServiceTest
{
    private NumberComputeService numberComputeService;

    @BeforeEach
    void setUp()
    {
        numberComputeService = new NumberComputeService();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void add_when_null_or_empty_should_return_0(String numbers)
    {
        // WHEN
        var computedNumber = numberComputeService.add(numbers);

        // THEN
        assertThat(computedNumber).isEqualTo(0);
    }

    @Test
    void add_when_single_digit_should_return_digit()
    {
        // GIVEN
        var numbers = "5";

        // WHEN
        var computedNumber = numberComputeService.add(numbers);

        // THEN
        assertThat(computedNumber).isEqualTo(5);
    }

    @Test
    void add_when_single_number_several_digits_should_return_number()
    {
        // GIVEN
        var numbers = "314271";

        // WHEN
        var computedNumber = numberComputeService.add(numbers);

        // THEN
        assertThat(computedNumber).isEqualTo(314271);
    }

    @Test
    void add_when_two_single_digit_numbers_should_return_sum()
    {
        // GIVEN
        var numbers = "5,9";

        // WHEN
        var computedNumber = numberComputeService.add(numbers);

        // THEN
        assertThat(computedNumber).isEqualTo(14);
    }

    @Test
    void add_when_single_digit_and_several_digit_numbers_should_return_sum()
    {
        // GIVEN
        var numbers = "5,178";

        // WHEN
        var computedNumber = numberComputeService.add(numbers);

        // THEN
        assertThat(computedNumber).isEqualTo(183);
    }

    @Test
    void add_when_several_digit_and_single_digit_numbers_should_return_sum()
    {
        // GIVEN
        var numbers = "178,5";

        // WHEN
        var computedNumber = numberComputeService.add(numbers);

        // THEN
        assertThat(computedNumber).isEqualTo(183);
    }

    @Test
    void add_when_several_digit_numbers_should_return_sum()
    {
        // GIVEN
        var numbers = "178,5431";

        // WHEN
        var computedNumber = numberComputeService.add(numbers);

        // THEN
        assertThat(computedNumber).isEqualTo(5609);
    }
}