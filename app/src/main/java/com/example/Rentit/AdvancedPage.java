package com.example.Rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdvancedPage extends AppCompatActivity {


    DatabaseHandler objectDatabaseHandler;
    RecyclerView objectRecyclerView;
    RViewAdapter objectRViewAdapter;


    TextView Entered_Code;
    String CodeInstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_page);
    }

    public void updateThisPost(View view) {
        Intent jumptoupdatepage = new Intent(AdvancedPage.this, Update.class);
        startActivity(jumptoupdatepage);
    }

    public void deleteThisPost(View view) {
        Intent jumptodeletepost = new Intent(AdvancedPage.this, Delete.class);
        startActivity(jumptodeletepost);
    }

    public void LogOut(View view) {
        SessionManagement sessionManagement =new SessionManagement(AdvancedPage.this);
        sessionManagement.removeSession();
        Intent outofthesystem =new Intent(AdvancedPage.this,MainActivity.class);
        startActivity(outofthesystem);
    }

    public void moveToHomepage(View view) {
        Intent jumptohomepage = new Intent(AdvancedPage.this, HomeScreen.class);
        startActivity(jumptohomepage);
    }
}
