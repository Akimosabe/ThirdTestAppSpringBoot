package ru.example2.SecondTestAppSpringBoot.Service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.example2.SecondTestAppSpringBoot.Exception.ValidationFailedException;

@Service
public class RequestValidationService implements ValidationService {
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new
                    ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}