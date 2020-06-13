package com.example.Rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChooseViewOrUpload extends AppCompatActivity {

    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_home()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new Fragment();
                            break;
                        case R.id.nav_notification:
                            selectedFragment = new notification_fragment() ;
                            break;
                        case R.id.nav_setting:
                            selectedFragment = new setting_fragment() ;
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };
    public void ViewProperty(View view) {

        Intent jumptoviewpropertydetails = new Intent(getApplicationContext(),HomeScreen.class );
        startActivity(jumptoviewpropertydetails);
    }

    public void UploadProperty(View view) {
        Intent jumptouploadpropertydetails= new Intent(this,UploadPropertyHere.class);
        startActivity(jumptouploadpropertydetails);
    }




}




