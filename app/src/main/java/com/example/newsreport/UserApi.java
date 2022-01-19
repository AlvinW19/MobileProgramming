package com.example.newsreport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("user.json")
    Call<List<User>> getAllUser();
}
