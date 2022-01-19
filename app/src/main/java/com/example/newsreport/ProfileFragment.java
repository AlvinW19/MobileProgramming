package com.example.newsreport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        TextView name_text_view = view.findViewById(R.id.name_text_view);
        TextView email_text_view = view.findViewById(R.id.email_text_view);
        TextView gender_text_view = view.findViewById(R.id.gender_text_view);
        name_text_view.setText(sharedPreferences.getString(LoginActivity.PREF_NAME,"Name"));
        email_text_view.setText(sharedPreferences.getString(LoginActivity.PREF_EMAIL,"Email"));
        gender_text_view.setText(sharedPreferences.getString(LoginActivity.PREF_GENDER,"Gender"));
        return view;
    }

}
