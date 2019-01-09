package com.nanda.databindingexample.data.response.booklist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksResponse {

    @SerializedName("totalItems")
    private int totalItems;

    @SerializedName("kind")
    private String kind;

    @SerializedName("items")
    private List<BooksModel> booksModel;

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public List<BooksModel> getBooksModel() {
        return booksModel;
    }

    public void setBooksModel(List<BooksModel> booksModel) {
        this.booksModel = booksModel;
    }
}