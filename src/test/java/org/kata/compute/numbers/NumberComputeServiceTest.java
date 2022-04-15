package org.kata.compute.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;

class NumberComputeServiceTest {
    private NumberComputeService numberComputeService;

    @BeforeEach
    void setUp()
    {
        numberComputeService = new NumberComputeService();
    }

    @Nested
    class Step1Test {

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

    @Nested
    class Step2Test {

        @Test
        void add_when_three_single_digit_numbers_should_return_sum()
        {
            // GIVEN
            var numbers = "2,1,7";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10);
        }

        @Test
        void add_when_three_several_digit_numbers_should_return_sum()
        {
            // GIVEN
            var numbers = "784,10000,23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10807);
        }

        @Test
        void add_when_four_numbers_should_return_sum()
        {
            // GIVEN
            var numbers = "45,9999,732,7";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10783);
        }

        @Test
        void add_when_ten_several_digit_numbers_should_return_sum()
        {
            // GIVEN
            var numbers = "12,3,488,15,94,1,74,3,842,10";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(1542);
        }
    }

    @Nested
    class Step3Test {

        @Test
        void add_when_two_single_digit_numbers_with_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "5\n9";

            // WHEN
            var computedNumber = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumber).isEqualTo(14);
        }

        @Test
        void add_when_single_digit_and_several_digit_numbers_with_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "5\n178";

            // WHEN
            var computedNumber = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumber).isEqualTo(183);
        }

        @Test
        void add_when_several_digit_and_single_digit_numbers_with_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "178\n5";

            // WHEN
            var computedNumber = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumber).isEqualTo(183);
        }

        @Test
        void add_when_several_digit_numbers_with_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "178\n5431";

            // WHEN
            var computedNumber = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumber).isEqualTo(5609);
        }

        @Test
        void add_when_three_single_digit_numbers_with_coma_and_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "2,1\n7";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10);
        }

        @Test
        void add_when_three_single_digit_numbers_with_linefeed_and_coma_should_return_sum()
        {
            // GIVEN
            var numbers = "2\n1,7";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10);
        }

        @Test
        void add_when_three_single_digit_numbers_with_two_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "2\n1\n7";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10);
        }

        @Test
        void add_when_three_several_digit_numbers_with_comma_and_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "784,10000\n23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10807);
        }

        @Test
        void add_when_three_several_digit_numbers_with_linefeed_and_comma_should_return_sum()
        {
            // GIVEN
            var numbers = "784\n10000,23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10807);
        }

        @Test
        void add_when_three_several_digit_numbers_with_two_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "784\n10000\n23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(10807);
        }
    }

    @Nested
    class Step4Test {

        @Test
        void add_when_specify_single_char_delimiter_should_use_delimiter() {
            // GIVEN
            var numbers = "// \n45 16 78";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(139);
        }

        @Test
        void add_when_specify_single_char_delimiter_should_use_delimiter_with_comma_and_linefeed() {
            // GIVEN
            var numbers = "// \n45,16 78\n11";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(150);
        }
    }
}