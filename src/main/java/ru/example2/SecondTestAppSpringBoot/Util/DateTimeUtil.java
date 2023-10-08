package ru.example2.SecondTestAppSpringBoot.Util;

import java.text.SimpleDateFormat;

public class DateTimeUtil {

    public static SimpleDateFormat getCustomFormat() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }
}
