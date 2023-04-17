package com.example.news;

public class newsmodel {
    String thumbnail ,description,url;

    public newsmodel(String thumbnail, String description, String url) {
        this.thumbnail = thumbnail;
        this.description = description;
        this.url = url;
    }
    public newsmodel() {

    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
