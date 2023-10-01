package ru.example2.SecondTestAppSpringBoot.Service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.example2.SecondTestAppSpringBoot.Exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

}
