package com.nanda.databindingexample.utils;


import com.nanda.databindingexample.app.AppConstants;
import com.nanda.databindingexample.app.AppController;
import com.nanda.databindingexample.data.exception.ApiHttpException;
import com.nanda.databindingexample.data.exception.AppException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class RxJavaUtils {
    public static <T> ObservableTransformer<T, T> applyObserverSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static CompletableTransformer applyCompletableSchedulers() {
        return new CompletableTransformer() {
            @Override
            public CompletableSource apply(Completable completable) {
                return completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> ObservableTransformer<T, T> applyErrorTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.onErrorResumeNext(throwable -> {
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
        };
    }

    public static <T> ObservableTransformer<T, T> applyOnErrorCrasher() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
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
        };
    }
}
