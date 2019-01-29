package com.nanda.databindingexample.data.response.booklist;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BooksModel extends RealmObject {

    @SerializedName("kind")
    private String kind;

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    @SerializedName("etag")
    private String etag;

    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("selfLink")
    private String selfLink;

    private transient boolean isFromCreation;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getEtag() {
        return etag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public boolean isFromCreation() {
        return isFromCreation;
    }

    public void setFromCreation(boolean fromCreation) {
        isFromCreation = fromCreation;
    }
}