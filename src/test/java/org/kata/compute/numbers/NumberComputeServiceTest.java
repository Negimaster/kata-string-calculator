package org.kata.compute.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.kata.compute.numbers.exception.NegativeNumbersForbiddenException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
            var numbers = "311";

            // WHEN
            var computedNumber = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumber).isEqualTo(311);
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
            var numbers = "178,431";

            // WHEN
            var computedNumber = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumber).isEqualTo(609);
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
            var numbers = "784,1000,23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(1807);
        }

        @Test
        void add_when_four_numbers_should_return_sum()
        {
            // GIVEN
            var numbers = "45,999,732,7";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(1783);
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
            var numbers = "178\n531";

            // WHEN
            var computedNumber = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumber).isEqualTo(709);
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
            var numbers = "784,1000\n23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(1807);
        }

        @Test
        void add_when_three_several_digit_numbers_with_linefeed_and_comma_should_return_sum()
        {
            // GIVEN
            var numbers = "784\n1000,23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(1807);
        }

        @Test
        void add_when_three_several_digit_numbers_with_two_linefeed_should_return_sum()
        {
            // GIVEN
            var numbers = "784\n1000\n23";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(1807);
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

    @Nested
    class Step5Test {

        @Test
        void add_when_single_negative_number_should_throw_NegativeNumbersForbiddenException_and_contain_number() {
            // GIVEN
            var numbers = "-42";

            // WHEN / THEN
            assertThatThrownBy(() -> numberComputeService.add(numbers))
                    .isExactlyInstanceOf(NegativeNumbersForbiddenException.class)
                    .hasMessageContaining("-42");
        }

        @Test
        void add_when_several_negative_number_should_throw_NegativeNumbersForbiddenException_and_contain_all_numbers() {
            // GIVEN
            var numbers = "-42,-6,-12";

            // WHEN / THEN
            assertThatThrownBy(() -> numberComputeService.add(numbers))
                    .isExactlyInstanceOf(NegativeNumbersForbiddenException.class)
                    .hasMessageContainingAll("-42", "-6", "-12");
        }

        @Test
        void add_when_several_negative_number_should_throw_NegativeNumbersForbiddenException_and_contain_only_negative_numbers() {
            // GIVEN
            var numbers = "789,-42,6,-12";

            // WHEN / THEN
            assertThatThrownBy(() -> numberComputeService.add(numbers))
                    .isExactlyInstanceOf(NegativeNumbersForbiddenException.class)
                    .hasMessageContainingAll("-42", "-12")
                    .hasMessageNotContainingAny("6", "789");
        }

        @Test
        void add_when_with_delimiter_several_negative_number_should_throw_NegativeNumbersForbiddenException_and_contain_only_negative_numbers() {
            // GIVEN
            var numbers = "//a\n789,-42a6,-12";

            // WHEN / THEN
            assertThatThrownBy(() -> numberComputeService.add(numbers))
                    .isExactlyInstanceOf(NegativeNumbersForbiddenException.class)
                    .hasMessageContainingAll("-42", "-12")
                    .hasMessageNotContainingAny("6", "789");
        }
    }
    
    @Nested
    class Step6Test {

        // In english, "greater" is strict, as opposed to french
        @Test
        void add_when_one_thousand_should_return_1000() {
            // GIVEN
            var numbers = "1000";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(1000);
        }

        @Test
        void add_when_more_than_one_thousand_should_return_0() {
            // GIVEN
            var numbers = "1001";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(0);
        }

        @Test
        void add_when_add_more_than_one_thousand_should_return_0() {
            // GIVEN
            var numbers = "42,1001,12,95563";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(54);
        }
    }

    @Nested
    class Step7Test {

        @Test
        void add_when_specify_multiple_char_delimiter_should_use_delimiter() {
            // GIVEN
            var numbers = "//***\n45***16***78";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(139);
        }

        @Test
        void add_when_specify_multiple_char_delimiter_should_use_delimiter_with_comma_and_linefeed() {
            // GIVEN
            var numbers = "//****\n45,16****78\n11";

            // WHEN
            var computedNumbers = numberComputeService.add(numbers);

            // THEN
            assertThat(computedNumbers).isEqualTo(150);
        }
    }
}