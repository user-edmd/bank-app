package com.edmond.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status) {
        return new ResponseEntity<>(status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
        return new ResponseEntity<>(responseObj, status);
    }
}