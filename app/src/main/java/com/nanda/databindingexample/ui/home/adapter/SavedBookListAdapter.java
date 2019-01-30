package com.nanda.databindingexample.ui.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanda.databindingexample.R;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.databinding.ItemBookBinding;

import java.util.ArrayList;
import java.util.List;

public class SavedBookListAdapter extends RecyclerView.Adapter<SavedBookListAdapter.BooksViewHolder> {

    private List<BooksModel> booksModelList;

    public SavedBookListAdapter() {
        booksModelList = new ArrayList<>();
    }

    public void setBooksModelList(List<BooksModel> itemList) {
        if (itemList == null) {
            return;
        }
        booksModelList.clear();
        booksModelList.addAll(itemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BooksViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book,
                        viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder booksViewHolder, int position) {
        BooksModel model = booksModelList.get(position);
        ItemBookBinding binding = ((BooksViewHolder) booksViewHolder).getBinding();
        binding.setModel(model);
    }

    @Override
    public int getItemCount() {
        return booksModelList.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {

        private ItemBookBinding binding;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.imgAdd.setVisibility(View.GONE);
        }

        ItemBookBinding getBinding() {
            return binding;
        }
    }

}
