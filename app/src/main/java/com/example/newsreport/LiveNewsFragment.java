package com.example.newsreport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huawei.agconnect.AGConnectOptionsBuilder;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

public class LiveNewsFragment extends Fragment {

    private static final String TAG = "NavigationActivity";
    private EditText token_edit_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_news,container,false);
        token_edit_text = view.findViewById(R.id.token_edit_text);
        Button token_button = view.findViewById(R.id.token_button);
        token_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPushToken();
            }
        });
        return view;
    }

    private void showToken(String token){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                token_edit_text.setText(token);
            }
        });
    }

    private void sendRegTokenToServer(String token){
        Log.i(TAG,"sendRegTokenToServe: "+token);
    }

    private void getPushToken(){
        new Thread() {
            @Override
            public void run() {
                try {
                    String appId = new AGConnectOptionsBuilder().build(getActivity()).getString("client/app_id");
                    String token = HmsInstanceId.getInstance(getActivity()).getToken(appId,"HCM");
                    Log.i(TAG, "get token" + token);
                    if (!TextUtils.isEmpty(token)){
                        showToken(token);
                        sendRegTokenToServer(token);
                    }
                } catch (ApiException e){
                    Log.e(TAG, "get token failed, "+e);
                }
            }
        }.start();
    }
}
