package com.example.EmployeeAPI.advices;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ApiResponse<T> {

    private LocalTime timeStamp;

    private T data;
    private ApiResponse error;

    public ApiResponse() {
        this.data = data;
    }

    public ApiResponse(ApiError timeStamp) {
        this.timeStamp = LocalTime.now();
    }

    public ApiResponse(ApiResponse error) {
        this();
        this.error = error;
    }


}
