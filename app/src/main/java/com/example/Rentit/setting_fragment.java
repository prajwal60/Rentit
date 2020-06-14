package com.example.Rentit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class setting_fragment extends Fragment {

    Button logoutbutton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setting, container, false);
//                logoutbutton = (Button)getContext().findViewById(R.id.logOutOfTheApp);
//        logoutbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logOutOfTheApp();
//            }
//        });

        return view;
    }

//
//    public void logOutOfTheApp() {
//        SharedPreferences sharedPreferences = getSharedPreferences("rememberkey",MODE_PRIVATE);
//        sharedPreferences.edit().clear().commit();
//        Toast.makeText(HomeScreen.this, "You are logged out", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(HomeScreen.this, MainActivity.class));
//        finish();
//    }

}
