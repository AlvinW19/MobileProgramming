package com.example.newsreport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SportsNewsApi {
    @GET("sports-news.json")
    Call<List<News>> getAllNews();
}
