package com.stpl.tech.ss_service.ss_service.utilService.api;

import com.stpl.tech.ss_service.ss_service.config.annotations.SkipResponseWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return
                !returnType.getDeclaringClass().isAnnotationPresent(SkipResponseWrapper.class) &&
                        !returnType.hasMethodAnnotation(SkipResponseWrapper.class) &&
                        !ApiResponse.class.isAssignableFrom(returnType.getParameterType()) &&
                        !(ResponseEntity.class.isAssignableFrom(returnType.getParameterType()));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        if(body instanceof ResponseEntity<?> responseEntity) {
            HttpStatusCode httpStatus = responseEntity.getStatusCode();
            boolean isError = httpStatus.isError();
            String message = isError ? "Request failed" : "Request successful";
            return new ApiResponse<>(isError, message, responseEntity.getBody());
        }

        if (body instanceof String) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return "{\"success\":true,\"message\":\"Request successful\",\"data\":\"" + body + "\"}";
        }

        return new ApiResponse<>(true, "Request successful", body);
    }

}
