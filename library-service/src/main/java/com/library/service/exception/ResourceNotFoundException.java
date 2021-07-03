package com.library.service.exception;

import feign.Response;

public class ResourceNotFoundException extends RuntimeException {

    /*private Object resourceId;
    //private Response response;

    *//*public ResourceNotFoundException(Response feignResponse) {
        this.response = feignResponse;
    }*/

    public ResourceNotFoundException(Object resourceId) {
        super("Resource not found for given id");
    }

    /*public Response getResponse() {
        return response;
    }

    public void setResponse(Response feignResponse) {
        this.response = feignResponse;
    }*/
}
