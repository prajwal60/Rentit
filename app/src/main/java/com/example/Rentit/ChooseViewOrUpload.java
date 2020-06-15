package com.example.Rentit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChooseViewOrUpload extends AppCompatActivity {

    String UserID;
    Button logoutbutton;
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


    public void logOutOfTheApp(View view) {
        logoutbutton = (Button) findViewById(R.id.logOutOfTheApp);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOutOfTheApp();
            }
        });
    }

    public void logOutOfTheApp() {
        SharedPreferences sharedPreferences = getSharedPreferences("rememberkey",MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        Toast.makeText(ChooseViewOrUpload.this, "You are logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ChooseViewOrUpload.this, MainActivity.class));
        finish();
    }

    public void viewBooks(View view) {

        Intent jumptobookeddata = new Intent(getApplicationContext(), BookedData.class);
        startActivity(jumptobookeddata);
    }
}




