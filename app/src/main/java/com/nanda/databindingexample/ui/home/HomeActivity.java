package com.nanda.databindingexample.ui.home;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.nanda.databindingexample.R;
import com.nanda.databindingexample.base.BaseActivity;
import com.nanda.databindingexample.data.preferences.AppPreference;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.data.viewmodels.SavedBooksViewModel;
import com.nanda.databindingexample.databinding.ActivityHomeBinding;
import com.nanda.databindingexample.ui.home.adapter.SavedBookListAdapter;
import com.nanda.databindingexample.ui.plantlist.BookListActivity;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity
        implements LifecycleObserver, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    AppPreference appPreference;

    private RecyclerView recyclerView;
    private SavedBooksViewModel viewModel;
    private SavedBookListAdapter adapter;

    private ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setSupportActionBar(homeBinding.includeAppBar.toolbar);
        getLifecycle().addObserver(this);

        setHeaderTitle(getString(R.string.my_book));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, homeBinding.drawerLayout, homeBinding.includeAppBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        homeBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        adapter = new SavedBookListAdapter();
        homeBinding.navView.setNavigationItemSelectedListener(this);
        recyclerView = homeBinding.includeAppBar.includeContent.recyclerview;
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SavedBooksViewModel.class);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void fetchBookList() {
        viewModel.getSavedBookList()
                .observe(this, booksModelList -> {
                    if (booksModelList != null && booksModelList.size() > 0) {
                        updateRecyclerViews(booksModelList);
                    }
                });
    }

    private void updateRecyclerViews(List<BooksModel> booksModelList) {
        if (booksModelList != null && booksModelList.size() > 0) {
            homeBinding.includeAppBar.includeContent.setBooks(booksModelList);
            homeBinding.executePendingBindings();
            adapter.setBooksModelList(booksModelList);
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
}
