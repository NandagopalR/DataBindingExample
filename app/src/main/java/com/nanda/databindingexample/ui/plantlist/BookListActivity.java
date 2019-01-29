package com.nanda.databindingexample.ui.plantlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nanda.databindingexample.R;
import com.nanda.databindingexample.base.BaseActivity;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.data.response.common.ResponseStatus;
import com.nanda.databindingexample.data.viewmodels.BookListViewModel;
import com.nanda.databindingexample.databinding.ActivityBookListBinding;
import com.nanda.databindingexample.utils.UiUtils;

import java.util.List;

import javax.inject.Inject;

public class BookListActivity extends BaseActivity implements BookListAdapter.BookClickListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private BookListViewModel viewModel;
    private ActivityBookListBinding bookBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        bookBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_list);

        setSupportActionBar(bookBinding.toolbar);
        setHeaderTitle("Book List");
        showBackButton(true);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookListViewModel.class);

//        new BookListAdapter().setBookClickListener(this);
        observeLoadingStatus();
        fetchBookList();
    }

    private void fetchBookList() {
        viewModel.getBookList("hint").observe(this, response -> {
            if (response != null) {
                if (response.status == ResponseStatus.SUCCESS) {
                    List<BooksModel> booksModelList = (List<BooksModel>) response.data;
                    updateRecyclerViews(booksModelList);
                } else {
                    if (response != null && response.status == ResponseStatus.ERROR) {
                        UiUtils.showToast(BookListActivity.this, response.throwable.getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void onAddBook(BooksModel model) {
        viewModel.saveBookModel(model).subscribe(() -> {
            UiUtils.showToast(BookListActivity.this, "Book Saved");
        }, throwable -> {
            UiUtils.showToast(BookListActivity.this, throwable.getMessage());
        });
    }

    private void updateRecyclerViews(List<BooksModel> booksModelList) {
        if (booksModelList != null && booksModelList.size() > 0) {
            bookBinding.setBooks(booksModelList);
            bookBinding.executePendingBindings();
        }
    }

    private void observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object object) {
                Boolean isloading = (Boolean) object;
                if (isloading) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });
    }
}
