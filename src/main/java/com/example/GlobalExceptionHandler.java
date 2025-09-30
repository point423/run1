package com.example;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // JSON解析失败
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleJsonParseError(HttpMessageNotReadableException ex) {
        return new ErrorResponse("INVALID_JSON", "请求体格式错误");
    }

    // @Valid参数验证失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationError(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return new ErrorResponse("VALIDATION_FAILED", message);
    }

    // JSR-303验证失败
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining("; "));
        return new ErrorResponse("CONSTRAINT_VIOLATION", message);
    }

    // 数据库约束冲突
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return new ErrorResponse("DATABASE_CONSTRAINT_ERROR", ex.getRootCause().getMessage());
    }



    // 统一错误响应结构
    private static class ErrorResponse {
        private String code;
        private String message;

        // 构造方法
        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

        // Getter和Setter方法
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        // 可选：toString方法便于日志打印
        @Override
        public String toString() {
            return "ErrorResponse{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}