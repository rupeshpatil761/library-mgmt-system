package com.library.service.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GenericResponse {

    private boolean isSuccess;
    private String message;
    private int status;
    private Object payLoad;

    public GenericResponse(String message, Object payLoad){
        this.message = message;
        this.payLoad = payLoad;
    }

    public GenericResponse(String message, Integer status){
        this.message = message;
        this.status = status;
    }

    public GenericResponse(boolean isSuccess, String message, int status, Object payLoad) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.status = status;
        this.payLoad = payLoad;
    }
}
