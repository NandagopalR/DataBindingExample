package com.nanda.databindingexample.ui.home.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.nanda.databindingexample.data.response.booklist.BooksModel;

import java.util.ArrayList;
import java.util.List;

public class SavedBookListBindings {

    @BindingAdapter({"load_books"})
    public static void loadSavedBooks(@NonNull RecyclerView recyclerView, @NonNull List<BooksModel> booksModelList) {
        if (booksModelList == null) {
            booksModelList = new ArrayList<>();
        }
        SavedBookListAdapter adapter = new SavedBookListAdapter(booksModelList);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable errorDrawable) {
//        Picasso.get().load(url).error(error).into(view);
    }


}
