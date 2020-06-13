package com.example.Rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

            objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.extractDataAndImages());
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

                objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.searchByCode(CodeInstring));
                objectRecyclerView.setHasFixedSize(true);

                objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                objectRecyclerView.setAdapter(objectRViewAdapter);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void deleteThisPost(View view) {
        Intent jumptodeletepost = new Intent(HomeScreen.this, Delete.class);
        startActivity(jumptodeletepost);
    }

    public void updateThisPost(View view) {
        Intent jumptoupdatepage = new Intent(HomeScreen.this, Update.class);
        startActivity(jumptoupdatepage);
    }

    public void LogOut(View view) {
        SessionManagement sessionManagement =new SessionManagement(HomeScreen.this);
        sessionManagement.removeSession();
        Intent outofthesystem =new Intent(HomeScreen.this,MainActivity.class);
        startActivity(outofthesystem);
    }

    public void moveToAdvancedPage(View view) {
        Intent jumptoadvancedpage = new Intent(HomeScreen.this, AdvancedPage.class);
        startActivity(jumptoadvancedpage);
    }
}
