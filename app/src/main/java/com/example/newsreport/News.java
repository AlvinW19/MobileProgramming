package com.example.newsreport;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("id")
    private int id;

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("published")
    private String published;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;

    @SerializedName("source")
    private String source;

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublished() {
        return published;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }
}
