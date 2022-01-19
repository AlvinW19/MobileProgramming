package com.example.newsreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private BottomNavigationView bottom_navigation;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREF_NAME,MODE_PRIVATE);
        View headerView = navigationView.getHeaderView(0);
        TextView name_text_view = headerView.findViewById(R.id.name_text_view);
        TextView email_text_view = headerView.findViewById(R.id.email_text_view);
        name_text_view.setText(sharedPreferences.getString(LoginActivity.PREF_NAME,"Name"));
        email_text_view.setText(sharedPreferences.getString(LoginActivity.PREF_EMAIL,"Email"));

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if(item.getItemId()==R.id.nav_home){
                    setTitle("Home");
                    selectedFragment = new HomeFragment();
                }
                else if(item.getItemId()==R.id.nav_search){
                    setTitle("Search");
                    selectedFragment = new LiveNewsFragment();
                }
                else if(item.getItemId()==R.id.nav_profile){
                    setTitle("Profile");
                    selectedFragment = new ProfileFragment();
                }
                uncheckAllNavigationDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
        });
        setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        if(item.getItemId()==R.id.nav_about){
            setTitle("About");
            selectedFragment = new AboutFragment();
        }
        else if(item.getItemId()==R.id.nav_logout){
            SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREF_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(NavigationActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
        uncheckAllBottomNavigation();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    public void uncheckAllNavigationDrawer(){
        int size = navigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    public void uncheckAllBottomNavigation(){
        int size = bottom_navigation.getMenu().size();
        bottom_navigation.getMenu().setGroupCheckable(0, true, false);
        for (int i = 0; i < size; i++) {
            bottom_navigation.getMenu().getItem(i).setChecked(false);
        }
        bottom_navigation.getMenu().setGroupCheckable(0, true, true);
    }

}