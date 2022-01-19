package com.example.newsreport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthNewsListFragment extends Fragment {

    public final static String SHARED_PREF_NAME = "last_online_health_news";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list,container,false);
        HealthNewsApi healthNewsApi = ApiClient.getClient().create(HealthNewsApi.class);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        healthNewsApi.getAllNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> newsList = response.body();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(newsList);
                editor.putString(HomeFragment.PREF_LIST,json);
                editor.apply();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        String serializedObject = sharedPreferences.getString(HomeFragment.PREF_LIST,null);
        if(serializedObject != null){
            Gson gson = new Gson();
            Type type = new TypeToken<List<News>>(){}.getType();
            List<News> newsList;
            newsList = gson.fromJson(serializedObject, type);
            ListView listView = view.findViewById(R.id.list_view);
            ListViewAdapter listViewAdapter = new ListViewAdapter(newsList,getContext());
            listView.setAdapter(listViewAdapter);
        }
        return view;
    }
}
