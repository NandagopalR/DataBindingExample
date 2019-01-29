package com.nanda.databindingexample.ui.plantlist;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.nanda.databindingexample.data.response.booklist.BooksModel;

import java.util.ArrayList;
import java.util.List;

public class BookListBindings {

    @BindingAdapter({"load_server_books"})
    public static void loadServerBooks(@NonNull RecyclerView recyclerView, @NonNull List<BooksModel> booksModelList) {
        if (booksModelList == null) {
            booksModelList = new ArrayList<>();
        }
        BookListAdapter adapter = new BookListAdapter(booksModelList);
        recyclerView.setAdapter(adapter);
    }
}
