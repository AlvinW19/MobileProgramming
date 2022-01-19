package com.example.newsreport;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView news_image_view;
    TextView news_title_text_view, news_author_text_view, news_source_text_view, news_published_text_view, news_description_text_view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        news_image_view= itemView.findViewById(R.id.news_image_view);
        news_title_text_view = itemView.findViewById(R.id.news_title_text_view);
        news_author_text_view = itemView.findViewById(R.id.news_author_text_view);
        news_source_text_view = itemView.findViewById(R.id.news_source_text_view);
        news_published_text_view = itemView.findViewById(R.id.news_published_text_view);
        news_description_text_view = itemView.findViewById(R.id.news_description_text_view);
    }
}
