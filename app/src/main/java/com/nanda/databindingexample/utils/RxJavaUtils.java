package com.nanda.databindingexample.utils;

import com.nanda.databindingexample.app.AppConstants;
import com.nanda.databindingexample.app.AppController;
import com.nanda.databindingexample.data.exception.ApiHttpException;
import com.nanda.databindingexample.data.exception.AppException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Completable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaUtils {
    public static <T> Observable.Transformer<T, T> applyObserverSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Completable.Transformer applyCompletableSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> Observable.Transformer<T, T> applyErrorTransformer() {
        return observable -> observable.onErrorResumeNext((Func1<Throwable, Observable<? extends T>>) throwable -> {
            if (!NetworkUtils.isConnected(AppController.getInstance())) {
                return Observable.error(new RuntimeException(AppConstants.EXCEPTION_NO_NETWORK_CONNECTION));
            } else if (throwable instanceof UnknownHostException) {
                return Observable.error(new RuntimeException(AppConstants.EXCEPTION_REQUEST_TIMEOUT));
            } else if (throwable instanceof SocketTimeoutException) { // Slow Internet Connection
                return Observable.error(new RuntimeException(AppConstants.EXCEPTION_REQUEST_TIMEOUT));
            } else if (throwable instanceof ApiHttpException) {
                Response response = ((ApiHttpException) throwable).response();
                if (AppException.isAppException(response)) {
                    try {
                        return Observable.error(AppException.create(response));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return Observable.error(throwable);
        });
    }

    public static <T> Observable.Transformer<T, T> applyOnErrorCrasher() {
        return observable -> observable.doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                final Throwable checkpoint = new Throwable();
                StackTraceElement[] stackTrace = checkpoint.getStackTrace();
                StackTraceElement element = stackTrace[1]; // First element after `crashOnError()`
                String msg = String.format("onError() crash from subscribe() in %s.%s(%s:%s)",
                        element.getClassName(),
                        element.getMethodName(),
                        element.getFileName(),
                        element.getLineNumber());

                throw new OnErrorNotImplementedException(msg, throwable);
            }
        });
    }
}
