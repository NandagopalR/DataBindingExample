package com.nanda.databindingexample.ui.plantlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanda.databindingexample.R;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.databinding.ItemBookBinding;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BooksViewHolder> {

    private List<BooksModel> booksModelList;
    private BookClickListener listener;

    public BookListAdapter(List<BooksModel> itemList) {
        this.booksModelList = itemList;
    }

    public BookListAdapter() {
    }

    public void setBookClickListener(BookClickListener bookClickListener) {
        this.listener = bookClickListener;
    }

    public interface BookClickListener {
        void onAddBook(BooksModel model);
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
        binding.imgAdd.setVisibility(View.VISIBLE);
        binding.setModel(model);
    }

    @Override
    public int getItemCount() {
        return booksModelList.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemBookBinding binding;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.imgAdd.setVisibility(View.VISIBLE);
            binding.imgAdd.setOnClickListener(this);
        }

        ItemBookBinding getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_add:

                    int position = getAdapterPosition();
                    if (position < 0)
                        return;
                    if (listener != null) {
                        listener.onAddBook(booksModelList.get(position));
                    }
                    break;
            }
        }

//        @OnClick(R.id.img_add)
//        public void onViewClicked() {
//            int position = getAdapterPosition();
//            if (position < 0)
//                return;
//            if (listener != null) {
//                listener.onAddBook(booksModelList.get(position));
//            }
//        }
    }

}
