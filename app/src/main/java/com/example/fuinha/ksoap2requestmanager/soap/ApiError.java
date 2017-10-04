package com.example.fuinha.ksoap2requestmanager.soap;

public class ApiError{

    private String Message;

    public String getMessage() {
        return Message;
    }

    public ApiError setMessage(String message) {
        this.Message = message;
        return this;
    }
}
