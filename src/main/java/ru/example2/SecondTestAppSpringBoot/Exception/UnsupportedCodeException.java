package ru.example2.SecondTestAppSpringBoot.Exception;

public class UnsupportedCodeException extends Exception {
    public UnsupportedCodeException() {
        super("UID не должен быть 123");
    }
}