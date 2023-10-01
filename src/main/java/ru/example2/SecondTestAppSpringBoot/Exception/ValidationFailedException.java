package ru.example2.SecondTestAppSpringBoot.Exception;

public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) {
        super(message);
    }
}
