package com.nanda.databindingexample.data.exception;

import retrofit2.HttpException;
import retrofit2.Response;

public class ApiHttpException extends HttpException {
    public ApiHttpException(Response<?> response) {
        super(response);
    }
}
