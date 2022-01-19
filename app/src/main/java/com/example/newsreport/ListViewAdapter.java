package com.example.newsreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    List<News> newsList;
    Context context;

    public ListViewAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public News getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.news_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        News news = getItem(i);
        Picasso.get().load(news.getImage()).resize(120, 120) .centerCrop().into(viewHolder.news_image_view);
        viewHolder.news_title_text_view.setText(news.getTitle());
        viewHolder.news_author_text_view.setText(news.getAuthor());
        viewHolder.news_source_text_view.setText(news.getSource());
        viewHolder.news_published_text_view.setText(news.getPublished());
        viewHolder.news_description_text_view.setText(news.getDescription());

        return view;
    }
}
