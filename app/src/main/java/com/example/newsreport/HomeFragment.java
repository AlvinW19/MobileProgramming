package com.example.newsreport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    public final static String PREF_LIST = "PREF_LIST";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        ViewPager2 viewPager2 = view.findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager2.setAdapter(viewPagerAdapter);

        ArrayList<String> tabs_menu = new ArrayList<>();
        tabs_menu.add("Health");
        tabs_menu.add("Sports");
        tabs_menu.add("Travel");

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs_menu.get(position));
            }
        }).attach();

        return view;
    }

}
