package com.nanda.databindingexample.data.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppApi {

    @GET(ApiConstants.BOOKS_API)
    Observable<ResponseBody> getBooksList(@Query("q") String query, @Query("key") String apiKey);

}
