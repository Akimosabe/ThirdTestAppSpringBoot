package ru.example2.SecondTestAppSpringBoot.Service;

import org.springframework.stereotype.Service;
import ru.example2.SecondTestAppSpringBoot.Model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}