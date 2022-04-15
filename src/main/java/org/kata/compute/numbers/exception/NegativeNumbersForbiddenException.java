package org.kata.compute.numbers.exception;

public class NegativeNumbersForbiddenException extends IllegalArgumentException {
    public NegativeNumbersForbiddenException(String s) {
        super(s);
    }
}
