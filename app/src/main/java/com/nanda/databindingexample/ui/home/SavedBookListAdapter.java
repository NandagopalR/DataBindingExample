package com.nanda.databindingexample.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanda.databindingexample.R;
import com.nanda.databindingexample.data.response.booklist.BooksModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SavedBookListAdapter extends RecyclerView.Adapter<SavedBookListAdapter.BooksViewHolder> {

    private List<BooksModel> booksModelList;
    private BookClickListener listener;

    public SavedBookListAdapter(BookClickListener listener) {
        this.listener = listener;
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
        booksViewHolder.bindDataToView(model);
    }

    @Override
    public int getItemCount() {
        return booksModelList.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.img_add)
        ImageView imgAdd;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imgAdd.setVisibility(View.GONE);
        }

        public void bindDataToView(BooksModel model) {
            if (model != null && model.getVolumeInfo() != null) {
                tvTitle.setText(model.getVolumeInfo().getTitle());
                tvDesc.setText(model.getVolumeInfo().getDescription());
            }
        }

        @OnClick(R.id.img_add)
        public void onViewClicked() {
            int position = getAdapterPosition();
            if (position < 0)
                return;
            if (listener != null) {
                listener.onAddBook(booksModelList.get(position));
            }
        }
    }

}
