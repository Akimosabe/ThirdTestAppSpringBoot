package ru.example2.SecondTestAppSpringBoot.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example2.SecondTestAppSpringBoot.Exception.UnsupportedCodeException;
import ru.example2.SecondTestAppSpringBoot.Exception.ValidationFailedException;
import ru.example2.SecondTestAppSpringBoot.Model.*;
import ru.example2.SecondTestAppSpringBoot.Service.ModifyResponseService;
import ru.example2.SecondTestAppSpringBoot.Service.ValidationService;
import ru.example2.SecondTestAppSpringBoot.Util.DateTimeUtil;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController

public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        long startTime = System.currentTimeMillis();

        log.info("Received request: {}", request);


        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Generated response: {}", response);


        try {
            validationService.isValid(bindingResult);

            if (request.getUid().equals("123")) {
                throw new UnsupportedCodeException();
            }

        } catch (ValidationFailedException e) {
            log.error("Ошибка валидации: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            log.error("Неподдерждиваемое значение UID: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Неизвестная ошибка: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        log.info("Modified response: {}", response);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.info("Diff: {} ms", elapsedTime);

        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);

    }
}
