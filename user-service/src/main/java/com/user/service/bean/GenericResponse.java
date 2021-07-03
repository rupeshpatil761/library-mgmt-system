package com.user.service.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericResponse {

    private boolean isSuccess;
    private String message;
    private Integer status;
    private Object payLoad;

    GenericResponse(){}

    public GenericResponse(String message,Object payLoad){
        this.message = message;
        this.payLoad = payLoad;
    }

    public GenericResponse(String message, Integer status){
        this.message = message;
        this.status = status;
    }
}
