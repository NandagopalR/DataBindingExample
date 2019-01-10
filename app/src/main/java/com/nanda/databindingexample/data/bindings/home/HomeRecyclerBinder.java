package com.nanda.databindingexample.data.bindings.home;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.nanda.databindingexample.data.response.booklist.BooksModel;

import java.util.List;

public class HomeRecyclerBinder {

    @BindingAdapter("load_books")
    public static void loadHomeList(RecyclerView recyclerView, List<BooksModel> booksModelList) {

    }

    @BindingAdapter("load_profile")
    public static void loadProfileImages(ImageView imageView,String url){

//        Glide.with()

    }

}
