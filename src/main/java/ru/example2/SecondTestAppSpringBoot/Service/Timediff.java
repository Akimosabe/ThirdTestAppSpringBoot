package ru.example2.SecondTestAppSpringBoot.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.example2.SecondTestAppSpringBoot.Model.Request;
import ru.example2.SecondTestAppSpringBoot.Model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class Timediff {
    public static void diff(Request request, Response response) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
        String strDateRequest = request.getSystemTime();
        String strDateResponse = response.getSystemTime();
        try {
            Date dateRequest = simpleDateFormat.parse(strDateRequest);
            Date dateResponse = simpleDateFormat.parse(strDateResponse);

            long difference = dateResponse.getTime() - dateRequest.getTime();

            log.info("Time difference: ", difference);
        } catch (Exception e) {
            log.error("TimeDiffError", e);
        }
    }

}
