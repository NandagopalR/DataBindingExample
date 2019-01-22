package com.nanda.databindingexample.data.response.booklist;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class VolumeInfo extends RealmObject {

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("printType")
    private String printType;

    @SerializedName("previewLink")
    private String previewLink;

    @SerializedName("canonicalVolumeLink")
    private String canonicalVolumeLink;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("title")
    private String title;

    @SerializedName("imageLinks")
    private ImageLinks imageLinks;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("publishedDate")
    private String publishedDate;

    @SerializedName("maturityRating")
    private String maturityRating;

    @SerializedName("allowAnonLogging")
    private boolean allowAnonLogging;

    @SerializedName("contentVersion")
    private String contentVersion;

    @SerializedName("infoLink")
    private String infoLink;

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setAllowAnonLogging(boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public boolean isAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getInfoLink() {
        return infoLink;
    }
}