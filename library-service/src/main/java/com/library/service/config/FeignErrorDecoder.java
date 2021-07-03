package com.library.service.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.library.service.bean.GenericResponse;
import com.library.service.exception.ResourceAlreadyExistException;
import com.library.service.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class FeignErrorDecoder implements ErrorDecoder {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new ResourceNotFoundException("Resource not found for given id");
        } else if (response.status() == 409) {
            return new ResourceAlreadyExistException();
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }

    private String getResponseBodyAsString(final Response.Body body) {
        Reader reader = null;
        try {
            reader = body.asReader();
            //Easy way to read the stream and get a String object
            String result = CharStreams.toString(reader);
            ObjectMapper mapper = new ObjectMapper();
            //just in case you missed an attribute in the Pojo
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            //init the Pojo
            GenericResponse genericResponse = mapper.readValue(result,
                    GenericResponse.class);
            return genericResponse.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to parse body";
        } finally {
            //It is the responsibility of the caller to close the stream.
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
