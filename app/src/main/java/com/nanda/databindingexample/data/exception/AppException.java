package com.nanda.databindingexample.data.exception;

import java.io.IOException;

import retrofit2.Response;

public class AppException extends IOException {

    public AppException(String detailMessage) {
        super(detailMessage);
    }

    public static boolean isAppException(Response response) {
        return response.code() >= 400 && response.code() < 500;
    }

    public static AppException create(Response response) throws IOException {
        return new AppException(response.errorBody().string());
    }

}