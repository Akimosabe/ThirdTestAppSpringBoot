package ru.example2.SecondTestAppSpringBoot.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {
    @NotBlank(message = "UID обязателен")
    @Size(max = 32)
    private String uid;

    @NotBlank(message = "Идентификатор операции обязателен")
    @Size(max = 32)
    private String operationUid;

    private SysName systemName;

    @NotBlank(message = "Не указано время создания")
    private String systemTime;

    private String source;

    @Min(value = 1, message = "communicationId >= 1")
    @Max(value = 100000, message = "communicationId >= 100000")
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}


