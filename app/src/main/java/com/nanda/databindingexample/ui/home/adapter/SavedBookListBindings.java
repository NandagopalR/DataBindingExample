package com.nanda.databindingexample.ui.home.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.nanda.databindingexample.data.response.booklist.BooksModel;

import java.util.List;

public class SavedBookListBindings {

    @BindingAdapter("load_books")
    public static void loadSavedBooks(RecyclerView recyclerView, List<BooksModel> booksModelList) {
        SavedBookListAdapter adapter = new SavedBookListAdapter(null);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
//        Picasso.get().load(url).error(error).into(view);
    }

}
