package ru.example2.SecondTestAppSpringBoot.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.example2.SecondTestAppSpringBoot.Model.Response;
import ru.example2.SecondTestAppSpringBoot.Util.DateTimeUtil;

import java.util.Date;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        return response;
    }
}