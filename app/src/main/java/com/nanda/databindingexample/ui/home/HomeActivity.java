package com.nanda.databindingexample.ui.home;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nanda.databindingexample.R;
import com.nanda.databindingexample.base.BaseActivity;
import com.nanda.databindingexample.data.preferences.AppPreference;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.data.viewmodels.SavedBooksViewModel;
import com.nanda.databindingexample.ui.plantlist.BookListActivity;
import com.nanda.databindingexample.utils.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity
        implements LifecycleObserver, NavigationView.OnNavigationItemSelectedListener, SavedBookListAdapter.BookClickListener {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    AppPreference appPreference;

    private SavedBooksViewModel viewModel;
    private SavedBookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getLifecycle().addObserver(this);

        setHeaderTitle(getString(R.string.my_book));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SavedBooksViewModel.class);
        adapter = new SavedBookListAdapter(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void fetchBookList() {
        viewModel.getSavedBookList()
                .observe(this, booksModelList -> {
                    if (booksModelList != null && booksModelList.size() > 0) {
                        updateRecyclerViews(booksModelList);
                    } else {
                        UiUtils.showToast(HomeActivity.this, "" + booksModelList.size());
                    }
                });
    }

    private void updateRecyclerViews(List<BooksModel> booksModelList) {
        if (booksModelList != null && booksModelList.size() > 0) {
            tvNoData.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
            adapter.setBooksModelList(booksModelList);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_books) {

        } else if (id == R.id.nav_books_list) {
            startActivity(new Intent(HomeActivity.this, BookListActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAddBook(BooksModel model) {

    }
}
