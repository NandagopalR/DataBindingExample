package com.nanda.databindingexample.data.response.common;

import android.support.annotation.Nullable;

import static com.nanda.databindingexample.data.response.common.ResponseStatus.ERROR;
import static com.nanda.databindingexample.data.response.common.ResponseStatus.SUCCESS;

public class AppResponse<T> {

    public final int status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable throwable;

    private AppResponse(int status, @Nullable T data, @Nullable Throwable throwable) {
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> AppResponse<T> success(T data) {
        return new AppResponse<>(SUCCESS, data, null);
    }

    public static <T> AppResponse<T> error(Throwable error) {
        return new AppResponse<>(ERROR, null, error);
    }

}
