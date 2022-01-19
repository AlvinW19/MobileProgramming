package com.example.newsreport;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new HealthNewsListFragment();
        }
        else if(position==1){
            return new SportNewsListFragment();
        }
        else if(position==2){
            return new TravelNewsListFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
