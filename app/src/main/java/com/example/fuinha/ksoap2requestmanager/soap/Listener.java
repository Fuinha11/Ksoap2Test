package com.example.fuinha.ksoap2requestmanager.soap;

/**
 * Created by Fuinha on 06/09/2017.
 */

public interface Listener<T> {
    void notifySuccess(T response);
    void notifyError(ApiError error);
}
