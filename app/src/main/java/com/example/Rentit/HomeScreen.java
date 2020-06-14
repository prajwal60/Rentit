package com.example.Rentit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeScreen extends AppCompatActivity {

    DatabaseHandler objectDatabaseHandler;
    RecyclerView objectRecyclerView;
    RViewAdapter objectRViewAdapter;

    TextView Entered_Code;
    String CodeInstring;

    Button logoutbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        extractingData();

    }

    public void extractingData() {
        try {
            objectRecyclerView = findViewById(R.id.RVdetails);
            objectDatabaseHandler = new DatabaseHandler(this);

            objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.extractDataAndImages(), this);
            objectRecyclerView.setHasFixedSize(true);

            objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerView.setAdapter(objectRViewAdapter);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void searchCode(View view) {

        Entered_Code = (TextView) findViewById(R.id.searchbycode);
        CodeInstring = Entered_Code.getText().toString();

        if (CodeInstring.equals("showallposts")) {
            extractingData();
        } else {

            try {
                objectRecyclerView = findViewById(R.id.RVdetails);
                objectDatabaseHandler = new DatabaseHandler(this);

                objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.searchByCode(CodeInstring), this);
                objectRecyclerView.setHasFixedSize(true);

                objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                objectRecyclerView.setAdapter(objectRViewAdapter);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void moveToAdvancedPage(View view) {
        Intent jumptoadvancedpage = new Intent(HomeScreen.this, AdvancedPage.class);
        startActivity(jumptoadvancedpage);
    }

    public void moveToFragment(View view) {

        Intent jumptohomepage = new Intent(HomeScreen.this, ChooseViewOrUpload.class);
        startActivity(jumptohomepage);
    }
//
//    public void logOutOfTheApp() {
//        SharedPreferences sharedPreferences = getSharedPreferences("rememberkey",MODE_PRIVATE);
//        sharedPreferences.edit().clear().commit();
//        Toast.makeText(HomeScreen.this, "You are logged out", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(HomeScreen.this, MainActivity.class));
//        finish();
//    }

    public void logOutOfTheApp(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("rememberkey",MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        Toast.makeText(HomeScreen.this, "You are logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(HomeScreen.this, MainActivity.class));
        finish();
    }
}
