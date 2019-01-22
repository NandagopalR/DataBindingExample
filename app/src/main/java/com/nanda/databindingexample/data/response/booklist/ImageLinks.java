package com.nanda.databindingexample.data.response.booklist;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ImageLinks extends RealmObject {

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("smallThumbnail")
    private String smallThumbnail;

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }
}