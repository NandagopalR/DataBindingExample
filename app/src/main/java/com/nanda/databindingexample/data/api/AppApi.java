package com.nanda.databindingexample.data.api;

import com.nanda.databindingexample.data.response.booklist.BooksResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppApi {

    @GET(ApiConstants.BOOKS_API)
    Observable<BooksResponse> getBooksList(@Query("q") String query, @Query("key") String apiKey);

}
