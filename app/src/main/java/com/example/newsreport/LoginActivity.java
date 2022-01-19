package com.example.newsreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public final static String SHARED_PREF_NAME = "logged_in_user_info";
    public final static String PREF_NAME = "PREF_NAME";
    public final static String PREF_EMAIL = "PREF_EMAIL";
    public final static String PREF_GENDER = "PREF_GENDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email_edit_text = findViewById(R.id.email_edit_text);
        EditText password_edit_text = findViewById(R.id.password_edit_text);
        Button login_button = findViewById(R.id.login_button);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_edit_text.getText().toString();
                String password = password_edit_text.getText().toString();
                if(email.trim().isEmpty()||password.trim().isEmpty()){
                    if(email.trim().isEmpty()){
                        email_edit_text.setError("Please Input Your Email");
                    }
                    if(password.trim().isEmpty()){
                        password_edit_text.setError("Please Input Your Password");
                    }
                }
                else{
                    UserApi userApi = ApiClient.getClient().create(UserApi.class);
                    userApi.getAllUser().enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            List<User> users = response.body();
                            boolean emailValid = false;
                            for(User user : users){
                                if(email.equals(user.getEmail())){
                                    emailValid = true;
                                    if(password.equals(user.getPassword())){
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(PREF_NAME,user.getName());
                                        editor.putString(PREF_EMAIL,user.getEmail());
                                        editor.putString(PREF_GENDER,user.getGender());
                                        editor.apply();
                                    }
                                    else{
                                        password_edit_text.setError("Wrong Password");
                                    }
                                    break;
                                }
                            }
                            if(!emailValid){
                                email_edit_text.setError("Email Is Not Valid");
                            }
                            if (sharedPreferences.contains(PREF_EMAIL)){
                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,NavigationActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}