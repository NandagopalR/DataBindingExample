package com.nanda.databindingexample.ui.plantlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.nanda.databindingexample.R;
import com.nanda.databindingexample.base.BaseActivity;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.data.response.common.ResponseStatus;
import com.nanda.databindingexample.data.viewmodels.BookListViewModel;
import com.nanda.databindingexample.utils.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private BookListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setHeaderTitle("Book List");
        showBackButton(true);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookListViewModel.class);

        observeLoadingStatus();
        fetchBookList();
    }

    private void fetchBookList() {
        viewModel.getBookList("hint").observe(this, response -> {
            if (response != null) {
                if (response.status == ResponseStatus.SUCCESS) {
                    List<BooksModel> booksModelList = (List<BooksModel>) response.data;
                    UiUtils.showToast(BookListActivity.this, "" + booksModelList.size());
                } else {
                    if (response != null && response.status == ResponseStatus.ERROR) {
                        UiUtils.showToast(BookListActivity.this, response.throwable.getMessage());
                    }
                }
            }
        });
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
