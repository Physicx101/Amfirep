package com.example.fauziw97.taxapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.app.AppCompatActivity;

import static com.example.fauziw97.taxapp.LoginActivity.Auth;
import static com.example.fauziw97.taxapp.LoginActivity.MyPREFERENCES;

/**
 * Created by Fauziw97 on 9/12/17.
 */

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String Authen = sharedpreferences.getString(Auth,null);
        if (Authen != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
